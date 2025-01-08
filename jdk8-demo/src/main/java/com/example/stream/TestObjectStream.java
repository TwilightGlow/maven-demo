package com.example.stream;

import org.junit.Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author Javen
 * @date 2022/3/16
 */
public class TestObjectStream {

    @Test
    // 一般用于序列化与反序列化
    public void testObjectStream() throws IOException, ClassNotFoundException {
//        // 使用匿名内部类初始化的List无法进行序列化, 但是静态成员类可以进行序列化
//        // https://stackoverflow.com/questions/17804704/notserializableexception-on-anonymous-class
//        List<String> strings = new ArrayList<String>() {
//            private static final long serialVersionUID = -2006402659518890948L;
//            {
//                add("Javen1");
//                add("Javen2");
//                add("Javen3");
//            }
//        };
        List<String> strings = Arrays.asList("Javen1", "Javen2", "Javen3");
        Teacher teacher = new Teacher();
        teacher.setName("Javen");
        teacher.setAge(28);
//         try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/example/stream/list.txt");
//              ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
//             objectOutputStream.writeObject(strings);
// //            objectOutputStream.writeObject(teacher);
//             try (FileInputStream fileInputStream = new FileInputStream("src/main/java/com/example/stream/list.txt");
//                  ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
//                 Object object = objectInputStream.readObject();
//                 List<String> strings1 = (List<String>) object;
// //                Teacher strings1 = (Teacher) object;
//                 System.out.println(strings1);
//             }
//         }

        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(Paths.get("src/main/resources/object-stream.txt")));
             ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(Paths.get("src/main/resources/object-stream.txt")))) {
            // outputStream.writeObject(strings);
            outputStream.writeObject(teacher);
            Object object = inputStream.readObject();
            // List<String> strings1 = (List<String>) object;
            Teacher teacher1 = (Teacher) object;
            System.out.println(teacher1);
        }
    }
}
