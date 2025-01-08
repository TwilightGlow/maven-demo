package com.example.stream;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestStreamReader {

    /**
     * 编码方式：
     *      ASCⅡ：用一个字节的7位表示
     *      ISO8859-1：用一个字节的8位表示
     *      GB2312：中文编码表，最多两个字节编码所有字符
     *      GBK：中文编码升级，最多两个字节编码
     *      Unicode：国际标准码字符集，融合了所有字符，为每个字符分配唯一的字符码，所有的文字用两个字节表示
     *      UTF-8：变长的编码方式，可用1-4个字节表示一个字符，每次8个位传输数据（中文在UTF8中占3个字节）
     *      UTF-16：每次16个位传输数据
     */
    @Test
    // StreamReader和StreamWriter是字节流到字符流的桥梁，用于将字节流转换为字符流
    // 一般用于指定字节流编码格式并读取到字符流，如果不指定字符集编码，改解码过程会使用平台默认的字符编码，如: GBK
    public void testStreamReaderWriter() throws IOException {
        // try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/stream_reader_utf8.txt");
        //      FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/stream_writer_gbk.txt");
        //      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        //      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "gbk")) {
        //     // 这里推荐直接使用apache的commons-io来做，IOUtils.copy()
        //     char[] chars = new char[1024];
        //     int len;
        //     // !!!注意这里是char[]不是byte[], 因为本质上是字符流
        //     while ((len = inputStreamReader.read(chars)) != -1) {
        //         outputStreamWriter.write(chars, 0, len);
        //     }
        // }
        try (InputStreamReader inputStreamReader = new InputStreamReader(Files.newInputStream(Paths.get("src/main/resources/stream_reader_utf8.txt")), StandardCharsets.UTF_8);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Files.newOutputStream(Paths.get("src/main/resources/stream_writer_gbk.txt")), "gbk")) {
            // 这里推荐直接使用apache的commons-io来做，IOUtils.copy()
            char[] chars = new char[1024];
            int len;
            // !!!注意这里是char[]不是byte[], 因为本质上是字符流
            while ((len = inputStreamReader.read(chars)) != -1) {
                outputStreamWriter.write(chars, 0, len);
            }
        }
    }
}
