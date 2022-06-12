package com.ams.module.dl;

import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Component
public class DLModelManager {
    private static final Logger log = LoggerFactory.getLogger(DLModelManager.class);

    private static final Map<String, Integer> labelMap = new HashMap<>();
    private static final Map<Integer, String> labelIndexMap = new HashMap<>();
    private static Predictor<NDList, NDList> predictor;//model
    private static NDManager manager;//NDManager, manager all NDObject
    private static int accuracyCount = 0;
    private static int total = 0;
    private static final Shape imgShape = new Shape(224, 224, 3);
    private static final int arrayLength = 224 * 224 * 3;

    private static boolean isInit = false;

    @PostConstruct
    public void init() {
        try {
            //读取模型
            File modelFile = ResourceUtils.getFile("classpath:models");
            Criteria<NDList, NDList> criteria = Criteria.builder()
                    .setTypes(NDList.class, NDList.class)
                    .optModelPath(modelFile.toPath()) //模型路径
                    .build();
            ZooModel<NDList, NDList> model = ModelZoo.loadModel(criteria);
            predictor = model.newPredictor();
            File testDir = ResourceUtils.getFile("classpath:test");
            String[] labels = testDir.list();
            for (int i = 0; i < labels.length; i++) {
                labelMap.put(labels[i], i);
                labelIndexMap.put(i, labels[i]);
            }
            manager = NDManager.newBaseManager();
            isInit = true;
        } catch (Exception e) {
            log.error("load model error", e);
        }
    }


    //maybe contains oom problem?
    //the oom problem is fixed by using childManager,
    //but there is still high memory usage problem when predicting one image
    private static int predict(File img) {
        try (NDManager childManager = manager.newSubManager()) {
            Image image = ImageFactory.getInstance().fromFile(img.toPath());
            NDArray ar = image.toNDArray(childManager);
            ar = ar.toType(DataType.FLOAT32, true);
            ar = ar.div(255.0);//normalize
            if (!ar.getShape().equals(imgShape)) {
                NDArray flattenArr = ar.flatten();
                int arLength = (int) flattenArr.getShape().get(0);
                NDArray newArr = childManager.zeros(new Shape(arrayLength));
                float[] value = new float[arrayLength];
                int arrCount = Math.min(arLength, arrayLength);
                for (int i = 0; i < arrCount; i++) {
                    value[i] = flattenArr.getFloat(i);
                }
                newArr.set(value);
                ar = newArr.reshape(imgShape);
            }
            ar = ar.reshape(1, 224, 224, 3);//reshape to tf tensor
            NDList list = new NDList();
            list.attach(childManager);
            list.add(ar);
            NDList output_data = predictor.predict(list);
            NDArray res = output_data.get(0).get(0);

            //equals to argMax,but there is exception when calling NDArray.argMax()
            long total = res.getShape().getShape()[0];
            float maxVal = -1;
            int predictLabel = -1;
            for (int i = 0; i < total; i++) {
                float val = res.getFloat(i);
                if (val > maxVal) {
                    maxVal = val;
                    predictLabel = i;
                }
            }
            return predictLabel;
        } catch (Exception e) {
            log.error("predict error", e);
        }
        return -1;
    }

    public static String predictFromResources(String imgName, String label) {
        if (isInit) {
            try {
                File img = ResourceUtils.getFile("classpath:test/" + label + "/" + imgName);
                return predictFromResources(img);
            } catch (Exception e) {
                log.error("predictFromResources error", e);
            }
        }
        return null;
    }

    private static String predictFromResources(File resourceFile) {
        String label = resourceFile.getParentFile().getName();
        int predict = predict(resourceFile);
        int actual = labelMap.get(label);
        total++;
        if (predict == actual) {
            accuracyCount++;
        }
        if (total % 50 == 0) {
            log.info("predict accuracy [{}]", (float) accuracyCount / total);
        }
        return labelIndexMap.get(predict);
    }

}
