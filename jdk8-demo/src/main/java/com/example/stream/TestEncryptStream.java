package com.example.stream;


import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestEncryptStream {

    private void encrypt(String src, String desc) throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(src)));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(desc)))) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                for (int i = 0; i < len; i++) {
                    bytes[i] = (byte) (bytes[i] ^ 5);
                }
                bufferedOutputStream.write(bytes, 0, len);
            }
        }
    }

    @Test
    public void encryptPicture() throws IOException {
        encrypt("src/main/java/com/example/stream/picture.jpg", "src/main/java/com/example/stream/机密.jpg");
        encrypt("src/main/java/com/example/stream/机密.jpg", "src/main/java/com/example/stream/picture3.jpg");
    }

    public static void main(String[] args) {
        String aaa = "测试";
        byte[] bytes = aaa.getBytes(StandardCharsets.UTF_8);

        // 加密
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (bytes[i] ^ 5);
        }

        // 使用Base64编码
        String bbb = Base64.getEncoder().encodeToString(bytes);

        // 解密
        byte[] decodedBytes = Base64.getDecoder().decode(bbb);
        for (int i = 0; i < decodedBytes.length; i++) {
            decodedBytes[i] = (byte) (decodedBytes[i] ^ 5);
        }

        // 将解密后的字节数组转换为字符串
        String ccc = new String(decodedBytes, StandardCharsets.UTF_8);

        System.out.println(aaa);
        System.out.println(bbb);
        System.out.println(ccc);
    }
}
