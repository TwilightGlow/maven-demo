package com.example.algorithm.dp;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangjw54
 */
public class GroupAnagrams {

    @Test
    public void test() {
        String[] words = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(anagramsOpt(words));
    }

    private List<List<String>> anagrams(String[] words) {
        return new ArrayList<>(Arrays.stream(words).collect(Collectors.groupingBy(
                word -> {
                    char[] chars = word.toCharArray();
                    Arrays.sort(chars);
                    return new String(chars);
                }
        )).values());
    }

    private List<List<String>> anagramsOpt(String[] words) {
        // Map<Character, Integer> charCountMap = new HashMap<>();
        // for (char c : str.toCharArray()) {
        //     charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        // }
        return new ArrayList<>(Arrays.stream(words).collect(Collectors.groupingBy(
                word -> word.chars().mapToObj(c -> (char) c)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        )).values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            m.computeIfAbsent(new String(s), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(m.values());
    }

}
