package com.example.stream;

import org.junit.Test;

import java.io.*;

/**
 * @author Javen
 * @date 2022/3/17
 */
public class TestDataStream {

    @Test
    // DataStream是为了方便操作基本数据类型和String类型
    // DataInputStream可以从字节流中直接转换出具体的数据类型，DataOutputStream可以将各种类型的数据在内部转成byte字节然后利用FileOutputStream写入文件中
    public void testDataStream() throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("src/main/java/com/example/stream/data-stream.txt"));
             DataInputStream dataInputStream = new DataInputStream(new FileInputStream("src/main/java/com/example/stream/data-stream.txt"))) {
            dataOutputStream.writeUTF("张津玮1");
            dataOutputStream.writeUTF("张津玮2");
            dataOutputStream.writeUTF("张栩越");
            dataOutputStream.writeBoolean(true);
            dataOutputStream.flush();
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readBoolean());
        }
    }
}
