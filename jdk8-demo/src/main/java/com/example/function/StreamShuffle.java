package com.example.function;

import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.*;

/**
 * @author zhangjw54
 */
public class StreamShuffle {

    @Test
    public void eagerShuffleCollector() {
        List<String> source = IntStream.range(0, 100)
                .boxed()
                .map(Object::toString)
                .collect(Collectors.toList());

        List<String> result = source.stream()
                .collect(toEagerShuffledStream())
                .limit(50)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void lazyShuffleCollector() {
        List<String> source = IntStream.range(0, 100)
                .boxed()
                .map(Object::toString)
                .collect(Collectors.toList());

        List<String> result = source.stream()
                .collect(toLazyShuffledStream()) // 返回值是stream的会惰性执行，不会立即触发流的遍历和处理
                .limit(50)
                .collect(Collectors.toList()); // 返回值不是stream的会立即执行

        System.out.println(result);
    }

    @Test
    public void improvedShuffleCollector() {
        List<String> source = IntStream.range(0, 100)
                .boxed()
                .map(Object::toString)
                .collect(Collectors.toList());

        // toImprovedShuffledStream() 并不知道它需要处理两次，它返回的是一个包含所有元素的 Stream。而实际的元素数量是在终端操作 collect(Collectors.toList()) 中确定的，由于之前的 limit(2) 限制，只有两个元素被收集
        List<String> result = source.stream()
                .collect(toImprovedShuffledStream())
                .limit(5).limit(2)
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    /**
     * 在 Stream API 的实现中，中间操作是按需执行的，而且在并行流的情况下，会使用一些优化策略来提高性能。以下是大致的实现流程：
     *
     * Lazy Evaluation（惰性计算）：
     *
     * Stream 中的中间操作（如 filter()）并不会立即执行，而是构建了一个包含操作的操作链。这些操作链被延迟执行，只有在遇到终端操作（如 collect()、forEach()）时才会触发实际的处理。
     * filter() 返回的是一个新的 Stream 对象，其中包含了筛选条件。
     * Pipeline（操作链）：
     *
     * 连续的中间操作构成了一个操作链（pipeline），每个操作都是一个环节。
     * 在串行流中，操作链是按照顺序执行的。在并行流中，操作链会被分割成多个子任务，这些子任务可以并行处理不同的元素。
     * Short-Circuiting（提前终止处理）：
     *
     * Stream API 支持一种称为 short-circuiting 的机制，即在找到符合条件的元素后提前终止处理。
     * 例如，limit(2) 就是一种 short-circuiting 操作，一旦找到两个满足条件的元素，就不再继续处理。
     * Parallel Processing（并行处理）：
     *
     * 在并行流中，不同的操作链可以并行执行，提高处理效率。每个子任务在独立的线程上执行，最后的结果会被合并。
     * 这些机制的结合使得 Stream API 能够高效地处理大量数据，并在需要时进行并行处理，同时保持代码的简洁性和灵活性。具体实现可能因不同的 JDK 版本而有所不同，但这些概念是通用的。
     *
     * 在串行流中，Short-Circuiting（提前终止处理）是通过迭代器（Iterator）的方式实现的。每个中间操作会逐个处理元素，而 Short-Circuiting 操作则会在满足某个条件时提前终止迭代。
     *
     * 让我们以 limit(n) 为例来说明 Short-Circuiting 的实现：
     *
     * limit(n) 实现：
     *
     * 当执行 limit(n) 时，它会创建一个新的迭代器，该迭代器在遍历元素时，只会处理前 n 个元素。
     * 这个迭代器会迭代到第 n 个元素后，就会发出一个信号，指示已经达到了限制。此时，后续的元素将不再被处理。
     * 当这个迭代器被用于下一个中间操作时，后续的中间操作就会感知到这个信号，从而提前终止处理。
     * 感知 Short-Circuiting：
     *
     * 每个中间操作都可以检查上游操作的状态，以确定是否可以提前终止处理。这是通过检查迭代器的状态来实现的。
     * 在迭代到达限制时，迭代器可以将状态设置为提前终止，并在后续的操作中进行检查。
     * 不同中间操作感知相同 Short-Circuiting：
     *
     * 在串行流中，由于操作是按顺序执行的，后续的中间操作可以感知到前面中间操作的提前终止状态。
     * 如果前面的中间操作（例如 limit(n)）提前终止了，后续的操作（例如 filter()）可以检查迭代器的状态，从而避免处理不必要的元素。
     * 总的来说，每个中间操作都可以感知上游迭代器的状态，以实现 Short-Circuiting。这种机制确保了在找到符合条件的元素后，后续的元素不再被处理，提高了性能和效率。
     */
    public void testLazy() {
        Stream<String> stream = Stream.of("1", "2", "3", "4");
        List<String> list = stream
                .limit(2)
                .filter(x -> Integer.parseInt(x) % 2 == 0)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    // Collections.shuffle()会对list中所有元素进行打乱，时间复杂度较高
    private <T> Collector<T, ?, Stream<T>> toEagerShuffledStream() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    Collections.shuffle(list);
                    return list.stream();
                });
    }

    // 构建自定义Spliterator实现随机遍历
    // 由于stream()惰性执行的特性，这里Spliterator的tryAdvance方法只会执行5次
    public <T> Collector<T, ?, Stream<T>> toLazyShuffledStream() {
        return Collectors.collectingAndThen(
                Collectors.toCollection(ArrayList::new),
                list ->
                        StreamSupport.stream(new RandomSpliterator<>(list, Random::new), false));
    }

    // 优化性能，不需要执行list.remove()，每次减少size即可
    public <T> Collector<T, ?, Stream<T>> toImprovedShuffledStream() {
        return Collectors.collectingAndThen(
                Collectors.toCollection(ArrayList::new),
                list ->
                        StreamSupport.stream(new ImprovedRandomSpliterator<>(list, Random::new), false));
    }

    static class RandomSpliterator<T, LIST extends RandomAccess & List<T>> implements Spliterator<T> {
        private final Random random;
        private final List<T> source;

        RandomSpliterator(LIST source, Supplier<? extends Random> random) {
            Objects.requireNonNull(source, "source can't be null");
            Objects.requireNonNull(random, "random can't be null");

            this.source = source;
            this.random = random.get();
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            int remaining = source.size();
            if (remaining > 0) {
                action.accept(source.remove(random.nextInt(remaining)));
                return true;
            } else {
                return false;
            }

        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return source.size();
        }

        @Override
        public int characteristics() {
            return SIZED;
        }
    }

    /**
     *
     这段代码定义了一个泛型类 ImprovedRandomSpliterator，其中 <T, LIST extends RandomAccess & List<T>> 是泛型参数的声明。让我解释一下：
        1. <T> 表示该类有一个泛型参数，命名为 T，它是这个类中使用的元素的类型。
        2. LIST extends RandomAccess & List<T> 是另一个泛型参数，其中 LIST 是一个扩展了 RandomAccess 和 List<T> 接口的类型。这意味着 LIST 必须是一个同时实现了 RandomAccess 和 List<T> 接口的类。
            RandomAccess 接口表示这个列表支持随机访问，即可以通过索引快速访问列表中的元素。
            List<T> 接口表示这个列表存储的元素类型是 T。
        这种泛型声明的目的是为了限制 ImprovedRandomSpliterator 类的使用，确保传递给它的列表类型必须支持随机访问，并且存储的元素类型符合泛型参数 T。这有助于在编译时捕获一些潜在的类型错误。
     */
    static class ImprovedRandomSpliterator<T, LIST extends RandomAccess & List<T>> implements Spliterator<T> {

        private final Random random;
        private final List<T> source;
        private int size;

        ImprovedRandomSpliterator(LIST source, Supplier<? extends Random> random) {
            Objects.requireNonNull(source, "source can't be null");
            Objects.requireNonNull(random, "random can't be null");

            this.source = source;
            this.random = random.get();
            this.size = this.source.size();
        }

        @Override
        public boolean tryAdvance(Consumer<? super T> action) {
            if (size > 0) {
                int nextIdx = random.nextInt(size);
                int lastIdx = --size;

                T last = source.get(lastIdx);
                T elem = source.set(nextIdx, last);
                action.accept(elem);
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Spliterator<T> trySplit() {
            return null;
        }

        @Override
        public long estimateSize() {
            return source.size();
        }

        @Override
        public int characteristics() {
            return SIZED;
        }
    }

}
