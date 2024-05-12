package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <pre>
 *     Homer非常喜欢两种类型的汉堡，一种汉堡需要花费 m分钟 ,另一种需要花费 n分钟。假如Homer总共只有 t分钟，你需要找到一种方式使得Homer 没有浪费任何时间的情况下 吃最大数量的汉堡
 *     (通俗来讲，假如吃a个第一种汉堡，吃b个第二种汉堡，首先找到一种方式 a * m + b * n = t,这是没有浪费时间(吃最大汉堡数量也就是说，使 a+b的值最大)
 * Input:
 *      -输入包含3个整数在一排，分别代表 m，n，t (0 < m，n，t < 10000)
 * Output:
 *      -输出Homer在没有浪费时间的情况下，吃的最大汉堡数量
 * Case 1: m=4，n=9，t=22.
 *      答案是 3，Homer吃2个9分钟的汉堡，以及一个 4分钟的汉堡
 * Case 2:m=4，n=9，t=54.
 *      答案是 11，Homer吃 9个 4分钟的汉堡，以及两个 9分钟的汉堡。
 *      (虽然吃 6个 9分钟的汉堡也可以消耗完54分钟，但是 11个汉堡更多)
 * </pre>
 *
 * @author zhangjw54
 */
public class Hamburger {

    int[] cache = new int[10000];

    @Test
    public void recursiveMethod() {
        Arrays.fill(cache, -2);
        System.out.println(recursive(4, 9, 54));
    }

    private int recursive(int m, int n, int t) {
        if (t == 0) return 0;
        if (t < 0) return -1;

        // 记录曾经检索过的值
        if (cache[t] != -2) {
            return cache[t];
        }

        int firstChoice = recursive(m, n, t - m);
        int secondChoice = recursive(m, n, t - n);
        if (firstChoice == -1 && secondChoice == -1) return -1;
        cache[t] = Math.max(firstChoice, secondChoice) + 1;
        return cache[t];
    }
}
