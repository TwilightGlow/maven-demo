package com.example.compare;

import cn.hutool.core.collection.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.ListUtils;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjw54
 */
@Slf4j
public class DataPartition {

    private static final int[] nums = {2, 7, 11, 15, 3, 6, 8, 9, 10, 12, 13, 14, 16, 17, 18, 19, 20, 21, 22, 23, 24};

    @Test
    public void partition() {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        new Partition<>(list, 5).forEach(System.out::println);
    }

    @Test
    public void partitionUtil() {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        ListUtils.partition(list, 5).forEach(System.out::println);
        ListUtil.partition(list, 5).forEach(System.out::println);
    }

    private static final class Partition<T> extends AbstractList<List<T>> {
        private final List<T> list;
        private final int size;

        private Partition(final List<T> list, final int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(final int index) {
            final int listSize = size();
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            }
            if (index >= listSize) {
                throw new IndexOutOfBoundsException("Index " + index + " must be less than size " +
                        listSize);
            }
            final int start = index * size;
            final int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        @Override
        public int size() {
            return (int) Math.ceil((double) list.size() / (double) size);
        }
    }
}
