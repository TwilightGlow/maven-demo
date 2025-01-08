package com.example.stream;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * DataInputStream
 * DataInputStream是一个包装在基础输入流（如FileInputStream）上的输入流，允许我们以机器无关的方式从基础输入流中读取Java的基本数据类型。DataInputStream实现了DataInput接口。
 *
 * 常用方法
 * readBoolean(): 从输入流中读取一个布尔值。
 * readByte(): 从输入流中读取一个字节的有符号值。
 * readChar(): 从输入流中读取一个字符。
 * readDouble(): 从输入流中读取一个双精度值。
 * readFloat(): 从输入流中读取一个单精度值。
 * readInt(): 从输入流中读取一个整数。
 * readLong(): 从输入流中读取一个长整数。
 * readShort(): 从输入流中读取一个短整数。
 * readUTF(): 从输入流中读取一个以UTF-8编码的字符串。
 *
 *
 * DataOutputStream
 * DataOutputStream是一个包装在基础输出流（如FileOutputStream）上的输出流，允许我们以机器无关的方式将Java的基本数据类型写入基础输出流。DataOutputStream实现了DataOutput接口。
 *
 * 常用方法
 * writeBoolean(boolean v): 向输出流中写入一个布尔值。
 * writeByte(int v): 向输出流中写入一个字节。
 * writeChar(int v): 向输出流中写入一个字符。
 * writeDouble(double v): 向输出流中写入一个双精度值。
 * writeFloat(float v): 向输出流中写入一个单精度值。
 * writeInt(int v): 向输出流中写入一个整数。
 * writeLong(long v): 向输出流中写入一个长整数。
 * writeShort(int v): 向输出流中写入一个短整数。
 * writeUTF(String str): 向输出流中写入一个以UTF-8编码的字符串。
 *
 * @author Javen
 * @date 2022/3/17
 */
public class TestDataStream {

    @Test
    // DataStream是为了方便操作基本数据类型和String类型
    // DataInputStream可以从字节流中直接转换出具体的数据类型
    // DataOutputStream可以将各种类型的数据在内部转成byte字节然后利用FileOutputStream写入文件中
    public void testDataStream() throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(Paths.get("src/main/resources/data-stream.txt")));
             DataInputStream dataInputStream = new DataInputStream(new ClassPathResource("data-stream.txt").getInputStream())) {
            dataOutputStream.writeUTF("Javen");
            dataOutputStream.writeBoolean(true);
            dataOutputStream.flush();
            System.out.println(dataInputStream.readUTF());
            System.out.println(dataInputStream.readBoolean());
        }
    }

}
