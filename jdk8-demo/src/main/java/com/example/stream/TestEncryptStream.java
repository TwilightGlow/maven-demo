package com.example.stream;


import org.junit.Test;

import java.io.*;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestEncryptStream {

    private void encrypt(String src, String desc) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(src);
             FileOutputStream fileOutputStream = new FileOutputStream(desc);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
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
}
