package com.example.algorithm.trie;

import org.junit.jupiter.api.Test;

/**
 * @author zhangjw54
 */
public class TrieTest {

    @Test
    public void count() {
        Trie trie = new Trie();

        // 插入单词
        trie.insert("苹果");
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        // 搜索单词
        System.out.println(trie.search("apple"));   // 输出 2
        System.out.println(trie.search("app"));     // 输出 1
        System.out.println(trie.search("banana"));  // 输出 1
        System.out.println(trie.search("appl"));    // 输出 0

        // 检查前缀
        System.out.println(trie.startsWith("app"));   // 输出 true
        System.out.println(trie.startsWith("ban"));   // 输出 true
        System.out.println(trie.startsWith("bat"));   // 输出 false

        // 删除单词
        trie.delete("app");
        System.out.println(trie.search("app"));       // 输出 0
        System.out.println(trie.search("apple"));     // 输出 2
        trie.delete("apple");
        System.out.println(trie.search("apple"));     // 输出 1
        trie.delete("apple");
        System.out.println(trie.search("apple"));     // 输出 0

        // 获取具有给定前缀的所有单词
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apraxia");
        trie.insert("apt");
        System.out.println(trie.getWordsWithPrefix("ap")); // 输出 [apple, app, apraxia, apt]
    }
}
