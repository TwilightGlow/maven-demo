package com.example.algorithm.trie;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangjw54
 */
@Data
public class TrieNode {

    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord;
    int count = 0;  // 记录以这个节点为结尾的单词的出现次数
}
