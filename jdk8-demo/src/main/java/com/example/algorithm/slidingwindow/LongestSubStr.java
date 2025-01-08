package com.example.algorithm.slidingwindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author zhangjw54
 */
public class LongestSubStr {

    private final String str = "abcbcabb";

    @Test
    public void test() {
        System.out.println(lengthOfLongestSubstring1(str));
        System.out.println(StrOfLongestSubstring1(str));
        System.out.println(lengthOfLongestSubstring2(str));
        System.out.println(StrOfLongestSubstring2(str));
    }

    private int lengthOfLongestSubstring1(String str) {
        int[] freq = new int[256];
        int left = 0, right = 0;
        int res = 0;
        while (right < str.length()) {
            if (freq[str.charAt(right)] == 0) {
                freq[str.charAt(right)]++;
                right++;
                res = Math.max(res, right - left);
            } else {
                freq[str.charAt(left)]--;
                left++;
            }
        }
        return res;
    }

    private String StrOfLongestSubstring1(String str) {
        int[] freq = new int[256];
        int left = 0, right = 0;
        int finalLeft = 0, finalRight = 0;
        int res = 0;
        while (right < str.length()) {
            if (freq[str.charAt(right)] == 0) {
                freq[str.charAt(right)]++;
                right++;
                // 这里用大于等于是返回最后一个不重复的子串，如果用大于则返回第一个不重复的子串
                if (right - left >= res) {
                    res = right - left;
                    finalLeft = left;
                    finalRight = right;
                }
            } else {
                freq[str.charAt(left)]--;
                left++;
            }
        }
        return str.substring(finalLeft, finalRight);
    }

    private int lengthOfLongestSubstring2(String str) {
        int[] freq = new int[256];
        int res = 0;
        for (int left = 0, right = 0; right < str.length(); right++) {
            freq[str.charAt(right)]++;
            while (freq[str.charAt(right)] > 1 && left < right) {
                freq[str.charAt(left)]--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    private String StrOfLongestSubstring2(String str) {
        int[] freq = new int[256];
        int m = 0, n = 0;
        int res = 0;
        for (int left = 0, right = 0; right < str.length(); right++) {
            freq[str.charAt(right)]++;
            while (freq[str.charAt(right)] > 1 && left < right) {
                freq[str.charAt(left)]--;
                left++;
            }
            if (right - left + 1 > res) {
                res = right - left + 1;
                m = left;
                n = right + 1;
            }
        }
        return str.substring(m, n);
    }
}
