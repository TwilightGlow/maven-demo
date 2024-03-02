package com.example.facade.cipher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Javen
 * @date 2024/3/2
 */
public class CipherFileReader {

    public String read(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            // 逐行读取文件内容
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator()); // 如果需要保留换行符
            }
            // 将StringBuilder转换为字符串
            String fileContent = stringBuilder.toString();
            // 打印文件内容
            System.out.println("读取的文件内容：" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
