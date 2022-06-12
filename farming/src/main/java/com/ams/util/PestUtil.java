package com.ams.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PestUtil {
    private static final Map<String, String> pesticideMap = new HashMap<>();
    private static final Map<String, Set<String>> pesticidePestMap = new HashMap<>();
    static {
        pesticideMap.put("cutworm", "敌敌畏混合药剂");
        pesticideMap.put("lasiocampidae", "敌敌畏混合药剂");
        pesticideMap.put("limax", "敌敌畏混合药剂");
        pesticideMap.put("lycaenidae", "敌敌畏混合药剂");
        pesticideMap.put("lymantriid", "敌敌畏混合药剂");
        pesticideMap.put("millipede", "敌敌畏混合药剂");
        pesticideMap.put("eucleid", "速灭杀丁混合药剂");
        pesticideMap.put("noctuidae", "速灭杀丁混合药剂");
        pesticideMap.put("notodontidae", "速灭杀丁混合药剂");
        pesticideMap.put("nymphalidae", "速灭杀丁混合药剂");
        pesticideMap.put("papilionidae", "速灭杀丁混合药剂");
        pesticideMap.put("inchworm", "敌百虫混合药剂");
        pesticideMap.put("pierid", "敌百虫混合药剂");
        pesticideMap.put("platypodidae", "敌百虫混合药剂");
        pesticideMap.put("psychid", "敌百虫混合药剂");
        pesticideMap.put("pyralidid", "敌百虫混合药剂");
        pesticideMap.put("sawfly", "敌百虫混合药剂");
        pesticideMap.put("scarab", "敌百虫混合药剂");
        pesticideMap.put("snail", "敌百虫混合药剂");
        pesticideMap.put("grub", "辛硫磷混合药剂");
        pesticideMap.put("sphingidae", "辛硫磷混合药剂");
        pesticideMap.put("termite", "辛硫磷混合药剂");
        pesticideMap.put("tetranychidae", "辛硫磷混合药剂");
        pesticideMap.put("thripidae", "辛硫磷混合药剂");
        pesticideMap.put("tingidae", "辛硫磷混合药剂");
        pesticideMap.put("tortricidae", "辛硫磷混合药剂");
        pesticideMap.put("fulgoridae", "氰戊菊酯混合药剂");
        pesticideMap.put("trunkborer", "氰戊菊酯混合药剂");
        pesticideMap.put("wireworm", "氰戊菊酯混合药剂");
        pesticideMap.put("zygaenidae", "氰戊菊酯混合药剂");
        pesticideMap.put("aleyrodidae", "氰戊菊酯混合药剂");
        pesticideMap.put("aphididae", "氰戊菊酯混合药剂");
        pesticideMap.put("arctiinae", "氰戊菊酯混合药剂");
        pesticideMap.put("bombyx", "氰戊菊酯混合药剂");
        pesticideMap.put("gryllotalpidae", "美曲膦脂混合药剂");
        pesticideMap.put("buprestida", "美曲膦脂混合药剂");
        pesticideMap.put("cicadellidae", "美曲膦脂混合药剂");
        pesticideMap.put("coccoidea", "美曲膦脂混合药剂");
        pesticideMap.put("cossidae", "美曲膦脂混合药剂");
        pesticideMap.put("cricket", "美曲膦脂混合药剂");
        pesticideMap.put("curculionidae", "美曲膦脂混合药剂");
        addPesticidePestMap("敌敌畏混合药剂", "cutworm");
        addPesticidePestMap("敌敌畏混合药剂", "lasiocampidae");
        addPesticidePestMap("敌敌畏混合药剂", "limax");
        addPesticidePestMap("敌敌畏混合药剂", "lycaenidae");
        addPesticidePestMap("敌敌畏混合药剂", "lymantriid");
        addPesticidePestMap("敌敌畏混合药剂", "millipede");
        addPesticidePestMap("速灭杀丁混合药剂", "eucleid");
        addPesticidePestMap("速灭杀丁混合药剂", "noctuidae");
        addPesticidePestMap("速灭杀丁混合药剂", "notodontidae");
        addPesticidePestMap("速灭杀丁混合药剂", "nymphalidae");
        addPesticidePestMap("速灭杀丁混合药剂", "papilionidae");
        addPesticidePestMap("敌百虫混合药剂", "inchworm");
        addPesticidePestMap("敌百虫混合药剂", "pierid");
        addPesticidePestMap("敌百虫混合药剂", "platypodidae");
        addPesticidePestMap("敌百虫混合药剂", "psychid");
        addPesticidePestMap("敌百虫混合药剂", "pyralidid");
        addPesticidePestMap("敌百虫混合药剂", "sawfly");
        addPesticidePestMap("敌百虫混合药剂", "scarab");
        addPesticidePestMap("敌百虫混合药剂", "snail");
        addPesticidePestMap("辛硫磷混合药剂", "grub");
        addPesticidePestMap("辛硫磷混合药剂", "sphingidae");
        addPesticidePestMap("辛硫磷混合药剂", "termite");
        addPesticidePestMap("辛硫磷混合药剂", "tetranychidae");
        addPesticidePestMap("辛硫磷混合药剂", "thripidae");
        addPesticidePestMap("辛硫磷混合药剂", "tingidae");
        addPesticidePestMap("辛硫磷混合药剂", "tortricidae");
        addPesticidePestMap("氰戊菊酯混合药剂", "fulgoridae");
        addPesticidePestMap("氰戊菊酯混合药剂", "trunkborer");
        addPesticidePestMap("氰戊菊酯混合药剂", "wireworm");
        addPesticidePestMap("氰戊菊酯混合药剂", "zygaenidae");
        addPesticidePestMap("氰戊菊酯混合药剂", "aleyrodidae");
        addPesticidePestMap("氰戊菊酯混合药剂", "aphididae");
        addPesticidePestMap("氰戊菊酯混合药剂", "arctiinae");
        addPesticidePestMap("氰戊菊酯混合药剂", "bombyx");
        addPesticidePestMap("美曲膦脂混合药剂", "gryllotalpidae");
        addPesticidePestMap("美曲膦脂混合药剂", "buprestida");
        addPesticidePestMap("美曲膦脂混合药剂", "cicadellidae");
        addPesticidePestMap("美曲膦脂混合药剂", "coccoidea");
        addPesticidePestMap("美曲膦脂混合药剂", "cossidae");
        addPesticidePestMap("美曲膦脂混合药剂", "cricket");
        addPesticidePestMap("美曲膦脂混合药剂", "curculionidae");
    }

    private static void addPesticidePestMap(String pestcide, String pest) {
        Set<String> pestSet = pesticidePestMap.get(pestcide);
        if (pestSet == null) {
            pestSet = new HashSet<>();
            pesticidePestMap.put(pestcide, pestSet);
        }
        pestSet.add(pest);
    }

    public static String getPesticideByPest(String pest){
        return pesticideMap.get(pest);
    }

    public static Set<String> getPestByPesticide(String pesticide){
        return pesticidePestMap.get(pesticide);
    }
}
