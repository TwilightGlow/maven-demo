package com.example.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javen
 * @date 2024/2/15
 *
 * CareTaker负责保存备忘录，但不能对备忘录的内容进行操作或者访问
 */
public class MementoCaretaker {
    // 定义一个集合来存储多个备忘录
    private final List<ChessmanMemento> mementoList = new ArrayList<>();

    // 恢复备忘录
    public ChessmanMemento getMemento(int i) {
        return mementoList.get(i);
    }

    // 保存备忘录
    public void saveMemento(ChessmanMemento memento) {
        mementoList.add(memento);
    }
}
