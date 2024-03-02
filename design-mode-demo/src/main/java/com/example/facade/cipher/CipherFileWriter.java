package com.example.facade.cipher;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Javen
 * @date 2024/3/2
 */
public class CipherFileWriter {

    public void write(String data, String fileDest) {
        try (FileWriter writer = new FileWriter(fileDest)) {
            writer.write(data);
            System.out.println("写入文件成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
