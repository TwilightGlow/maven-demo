package com.example.algorithm.trie;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangjw54
 */
@Data
public class Trie {

    private TrieNode root = new TrieNode();

    // 插入一个单词到前缀树，并记录出现次数
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.computeIfAbsent(c, k -> new TrieNode());
        }
        current.isEndOfWord = true;
        current.count++;
    }

    // 搜索一个单词在前缀树中的出现次数
    public int search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return 0;
            }
        }
        return current.isEndOfWord ? current.count : 0;
    }

    // 判断前缀是否存在于前缀树中
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return false;
            }
        }
        return true;
    }

    // 删除前缀树中的单词
    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    // 获取具有给定前缀的所有单词
    public List<String> getWordsWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        TrieNode current = root;
        for (char c : prefix.toCharArray()) {
            current = current.children.get(c);
            if (current == null) {
                return words;
            }
        }
        getWords(current, new StringBuilder(prefix), words);
        return words;
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            current.count--;
            return current.children.isEmpty();
        }
        char c = word.charAt(index);
        TrieNode node = current.children.get(c);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1);

        if (shouldDeleteCurrentNode) {
            current.children.remove(c);
            return current.children.isEmpty() && !current.isEndOfWord;
        }
        return false;
    }

    private void getWords(TrieNode node, StringBuilder prefix, List<String> words) {
        if (node.isEndOfWord) {
            words.add(prefix.toString());
        }
        for (Map.Entry<Character, TrieNode> entry : node.children.entrySet()) {
            prefix.append(entry.getKey());
            getWords(entry.getValue(), prefix, words);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }
}
