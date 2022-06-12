package com.ams.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.Base64;

public class Base64Util {
    public static File base64ConvertFile(String s) {
        String filePath = "/static";
        String fileName = System.currentTimeMillis() + ".jpg";
        BASE64Decoder decoder = new BASE64Decoder();
        File file = null;
        try {
            byte[] bytes = decoder.decodeBuffer(s);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {
                    bytes[i] += 256;
                }
            }
            String imageFilePath = filePath + fileName.replace("\\\\", "/");
            OutputStream out = new FileOutputStream(imageFilePath);
            out.write(bytes);
            out.flush();
            out.close();
            file = new File(imageFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String fileConvertBase64(File file) {
        byte[] data = null;
        // 读取图片字节数组
        try (InputStream inputStream = new FileInputStream(file)) {
            data = new byte[inputStream.available()];
            inputStream.read(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(data);
    }
}
