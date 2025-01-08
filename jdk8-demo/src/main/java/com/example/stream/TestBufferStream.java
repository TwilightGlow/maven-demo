package com.example.stream;


import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestBufferStream {

    @Test
    // 从设计模式上看，BufferedReader是FileReader的装饰类(装饰器模式)
    // BufferedInputStream装饰一个 InputStream 使之具有缓冲功能，
    // is要关闭只需要调用最终被装饰出的对象的 close()方法即可，因为它最终会调用真正数据源对象的 close()方法。
    // BufferedReader和BufferedWriter的close()方法会首先被调用，随后它们会调用各自包装的FileReader和FileWriter的close()方法。
    public void testBufferedReaderWriter() throws IOException {
        // BufferedReader和BufferedWriter各自拥有8192个字符的缓冲区
        // BufferedReader比FileReader多了readLine()方法，不仅能一次读取一个字符或字符数组，还可以读取一行
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/bufferedReader.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/bufferedWriter.txt"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        }
    }

    @Test
    public void testBufferedInputOutputStream() throws IOException {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get("src/main/resources/bufferedInputStream.jpg")));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get("src/main/resources/bufferedOutputStream.jpg")))) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
//                bufferedOutputStream.flush(); // 可以执行flush()来立刻将内存写入，但这样会频繁操作IO失去缓冲的意义，默认close()会自动调用flush()
            }
        }
    }

    @Test
    public void testScanCharacters() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/scan-text-input.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/scan-text-output.txt"))) {
            Map<Character, Integer> map = new HashMap<>();
            int c;
            while ((c = bufferedReader.read()) != -1) {
                char c1 = (char) c;
                // map.merge(c1, 1, Integer::sum);
                map.compute(c1, (k, v) -> v == null ? 1 : v + 1);
            }
            map.forEach((k, v) -> {
                try {
                    if (k.equals(' ')) {
                        bufferedWriter.write("空格 = " + v);
                    } else if (k.equals('\t')) {
                        bufferedWriter.write("tab键 = " + v);
                    } else if (k.equals('\r')) {
                        bufferedWriter.write("回车 = " + v);
                    } else if (k.equals('\n')) {
                        bufferedWriter.write("换行 = " + v);
                    } else {
                        bufferedWriter.write(k + " = " + v);
                    }
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
