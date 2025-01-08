package com.example.algorithm.reservior;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 找出出现频率最高的前k个元素，如果出现次数相同，按字典序正序
 *
 * @author zhangjw54
 */
public class TopKFrequent {

    private final String[] words = {"love", "i",  "leetcode", "i", "love", "coding", "coding", "coding"};
    private final int k = 3;

    @Test
    public void topKFrequent() {
        System.out.println(hashMapCount(words, k));
        System.out.println(priorityQueue(words, k));
    }


    public List<String> priorityQueue(String[] words, int k) {
        Map<String, Integer> countMap = Arrays.stream(words).collect(Collectors.toMap(word -> word, word -> 1, Integer::sum));

        // 小顶堆，按出现次数最少的排序，当数量相同时按字典倒序
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (o1, o2) -> o1.getValue().equals(o2.getValue()) ?
                        o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue()
        );

        // 小顶堆永远保持k个元素，当超过k个元素时，将新的元素与堆顶元素比较，如果新元素大于堆顶元素，将堆顶元素出堆，新元素入堆，这样堆中的元素永远可以保持为前k个最大的元素
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (pq.size() < k) {
                pq.offer(entry);
            } else {
                if (pq.comparator().compare(entry, pq.peek()) > 0) {
                    pq.poll();
                    pq.offer(entry);
                }
            }
        }

        // for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
        //     pq.offer(entry);
        //     if (pq.size() > k) {
        //         pq.poll();
        //     }
        // }

        List<String> ret = new ArrayList<>();
        while (!pq.isEmpty()) {
            ret.add(pq.poll().getKey());
        }

        Collections.reverse(ret);
        return ret;
    }

    public List<String> hashMapCount(String[] words, int k) {
        // Map<String, Integer> countMap = Arrays.stream(words).collect(
        //         HashMap::new,
        //         (map, word) -> map.put(word, map.getOrDefault(word, 0) + 1),
        //         HashMap::putAll
        // );
        Map<String, Long> countMap = Arrays.stream(words).collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        // 虽然Map是无序的，但是通过Stream遍历的是Map.Entry，可以使用sorted进行排序
        return countMap.entrySet().stream()
                .sorted((o1, o2) -> o1.getValue().equals(o2.getValue()) ?
                        o1.getKey().compareTo(o2.getKey()) : Long.compare(o2.getValue(), o1.getValue()))
                .map(Map.Entry::getKey)
                .limit(k)
                .collect(Collectors.toList());
    }
}
