package com.example.stream;


import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestBasicStream {

    @Test
    // 字符流专门用来处理文本类文件 (Reader <---> Writer)，不能处理其它类型数据文件
    // 字符流在操作时本身会用到缓存区（不关闭资源不会自动写入），如果想在不关闭前也输出可以调用flush()
    public void testFileReaderWriter() throws IOException {
        // try-with-resources对所有继承了AutoCloseable的接口生效
        // 所有需要自动关闭的资源，要在try-with-resources中单独声明，不能嵌套声明，否则无法自动关闭
        // 在try-with-resource中声明的变量会被隐式的声明为final
        try (FileReader fileReader = new FileReader("src/main/resources/fileReader.txt");
             FileWriter fileWriter = new FileWriter("src/main/resources/fileWriter.txt")) {
            char[] chars = new char[5];
            int len;
            while ((len = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
        }
    }

    // 在程序运行中File读取的是项目根目录的相对路径，或者绝对路径（Path from Repository Root / Absolute Path）
    // 如果使用@Test注解那File读取的是内容的相对路径（Path from Content Root）
    // FileReader不存在会报错，FileWriter不存在会自动创建
    public static void main(String[] args) throws IOException {
        try (FileReader fileReader = new FileReader("jdk8-demo/src/main/resources/fileReader.txt");
             // FileWriter fileWriter = new FileWriter("jdk8-demo/src/main/resources/fileWriter.txt")) {
             FileWriter fileWriter = new FileWriter("C:\\Users\\Jinwei Zhang\\IdeaProjects\\maven-demo\\jdk8-demo\\src\\main\\resources\\fileWriter.txt")) {
            char[] chars = new char[5];
            int len;
            while ((len = fileReader.read(chars)) != -1) {
                fileWriter.write(chars, 0, len);
            }
        }
    }

    @Test
    // 对于非文本类文件，用字节流处理 (InputStream <---> OutputStream)
    // 字节流在操作时本身不会用到缓存区
    // 这里IO流使用了装饰模式和适配器模式
    public void testInputOutputStream() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/fileInputStream.jpg");
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/fileOutputStream.jpg")) {
            // 这里推荐直接使用apache的commons-io来做，IOUtils.copy()
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, len);
            }
        }
    }
}
