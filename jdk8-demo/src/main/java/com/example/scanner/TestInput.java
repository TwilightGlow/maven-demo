package com.example.scanner;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Javen
 * @date 2022/3/12
 */
public class TestInput {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         while (true) {
//             System.out.print("请输入： ");
// //            int i = Integer.parseInt(scanner.nextLine());
//             int i = scanner.nextInt();
//             System.out.print("结果是： ");
//             System.out.println(i * 10);
//             if (i == 100) break;
//         }
//     }


    public static void main(String[] args) {
        int[] arr = new int[10];
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入10个数：");
        for (int i = 0; i < 10; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println("输入的数组是：" + Arrays.toString(arr));
    }
}
