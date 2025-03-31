package com.example.algorithm.sort;

import cn.hutool.core.util.ArrayUtil;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * <pre>
 *  快排的基本思想是: 通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
 *  <p>排序原理:
 *  <ul>
 *      <li>首先设定一个分界值，通过该分界值将数组分成左右两部分;</li>
 *      <li>将大于或等于分界值的数据放到到数组右边，小于分界值的数据放到数组的左边。此时左边部分中各元素都小于或等于分界值，而右边部分中各元素都大于或等于分界值;</li>
 *      <li>然后，左边和右边的数据可以独立排序。对于左侧的数组数据，又可以取一个分界值，将该部分数据分成左右两部分，同样在左边放置较小值，右边放置较大值。右侧的数组数据也可以做类似处理。</li>
 *      <li>重复上述过程，可以看出，这是一个递归定义。通过递归将左侧部分排好序后，再递归排好右侧部分的顺序。当左侧和右侧两个部分的数据排完序后，整个数组的排序也就完成了。</li>
 *  </ul>
 *
 *  <p>切分原理:
 *  <ol>
 *      <li>找一个基准值，用两个指针分别指向数组的头部和尾部;</li>
 *      <li>先从尾部向头部开始搜索一个比基准值小的元素，搜索到即停止，</li>
 *      <li>并记录指针的位置;</li>
 *      <li>再从头部向尾部开始搜索一个比基准值大的元素，搜索到即停止，并记录指针的位置;</li>
 *      <li>交换当前左边指针位置和右边指针位置的元素;</li>
 *      <li>重复2,3,4步骤，直到左边指针的值大于右边指针的值停止。</li>
 *  </ol>
 *
 *  <p>快速排序时间复杂度分析:
 *  <P>快速排序的一次切分从两头开始交替搜索，直到left和right重合，因此，一次切分算法的时间复杂度为O(n)但整个快速排序的时间复杂度和切分的次数相关。最优情况∶每一次切分选择的基准数字刚好将当前序列等分。
 *  <P>如果我们把数组的切分看做是一个树，那么上图就是它的最优情况的图示，共切分了logn次，所以，最优情况下快速排序的时间复杂度为O(nlogn); 最坏情况:每一次切分选择的基准数字是当前序列中最大数或者最小数，这使得每次切分都会有一个子组，那么总共就得切分n次，所以，最坏情况下，快速排序的时间复杂度为O(n^2);
 *  <P>平均情况:每一次切分选择的基准数字不是最大值和最小值，也不是中值，这种情况我们也可以用数学归纳法证明，快速排序的时间复杂度为O(nlogn),由于数学归纳法有很多数学相关的知识，容易使我们混乱，所以这里就不对平均情况的时间复杂度做证明了。
 *  <P>双边快排（Hoare 分区方案）通常在处理大量重复元素时表现更好的主要原因是它能够更有效地平衡分区，减少递归深度，从而避免性能退化。以下是详细解释：
 *
 *  <P>1. 分区平衡性
 *  Hoare 分区方案
 * 工作原理：Hoare 分区方案通过使用两个指针（一个从左到右，一个从右到左）来定位交换元素，直到两个指针相遇。这样可以确保所有小于基准值的元素被交换到基准值左侧，所有大于基准值的元素被交换到基准值右侧。
 * 处理重复元素：当遇到重复元素时，左右指针会继续移动而不会停下来，这使得分区能够相对均匀地处理大量重复元素。重复元素被分散到两侧的子数组中，避免了集中在一侧的情况。
 *  Lomuto 分区方案
 * 工作原理：Lomuto 分区方案使用一个指针从左向右移动，将小于基准值的元素交换到前面的位置，最后将基准值交换到中间指定位置。
 * 处理重复元素：当遇到重复元素时，这些元素会被处理成与其他较小或较大的元素一样，可能导致大量重复元素集中在一侧，从而导致不平衡分区。这会增加某些分区的大小，使递归深度增加，最终导致时间复杂度退化为 O(n^2)。
 *
 *  <P>2. 递归深度
 * Hoare 分区方案
 * 递归深度：由于 Hoare 分区方案能够更均匀地分区，每次递归处理的数组长度较为均衡，从而递归深度较浅。
 * 时间复杂度：在最佳和平均情况下，递归深度通常为 O(log n)，时间复杂度为 O(n log n)。
 * Lomuto 分区方案
 * 递归深度：如果分区不均衡，特别是遇到大量重复元素时，递归深度会增加。
 * 时间复杂度：在最坏情况下（如处理大量重复元素或接近有序的数组），递归深度可能接近 O(n)，导致时间复杂度退化为 O(n^2)。
 *
 *  <P>3. 交换次数
 * Hoare 分区方案
 * 交换次数：Hoare 分区方案在处理重复元素时，交换次数较少，因为大部分重复元素不会被频繁交换。指针移动到合适的位置后才进行交换，因此交换次数相对较少。
 * 效率：通过减少不必要的交换，提高了处理效率。
 * Lomuto 分区方案
 * 交换次数：在 Lomuto 分区方案中，重复元素可能会导致不必要的交换，因为每次遇到小于基准值的元素都会进行交换操作。
 * 效率：增加的交换次数会降低整体效率，特别是在处理大量重复元素时。
 * </pre>
 *
 * 快排是不稳定的排序算法，因为在排序的过程中，元素的相对位置会发生变化。
 * 例如 [5a, 3, 2, 5b, 1]，第一轮使用5a作为基准元素，排序后变为 [3, 2, 1, 5b, 5a]，5a和5b的相对位置发生了变化。
 *
 * @author zhangjw54
 */
public class QuickSort {

    int[] nums = {2, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void quickSort() {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        // 得到基准元素的位置，双边快排 Hoare分区方案
        int middle = partition(nums, start, end);

        // 单边快排 Lomuto分区方案
        // int middle = singlePartition(nums, start, end);
        // 分别对两部分进行递归排序
        quickSort(nums, start, middle - 1);
        quickSort(nums, middle + 1, end);
    }

    private int partition(int[] nums, int start, int end) {

        // 优化：三数取中法
        int mid = start + (end - start) / 2;
        if (nums[start] > nums[mid]) ArrayUtil.swap(nums, start, mid);
        if (nums[start] > nums[end]) ArrayUtil.swap(nums, start, end);
        if (nums[mid] > nums[end]) ArrayUtil.swap(nums, mid, end);
        ArrayUtil.swap(nums, mid, end);

        int pivot = nums[start]; // 基准元素
        int left = start + 1; // 左指针
        int right = end; // 右指针

        // 循环移动左右指针，当两个指针重合时，使得重合点左边的元素都小于基准元素，右边都大于基准元素
        while (left <= right) {
            // 当左指针大于基准元素，且右指针小于基准元素时，交换这两个数
            if (nums[left] > pivot && nums[right] < pivot) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
            // 控制左指针向右移动，找到大于基准元素的那个数
            if (nums[left] <= pivot) {
                left++;
            }
            // 控制右指针向左移动，找到小于基准元素的那个数
            if (nums[right] >= pivot) {
                right--;
            }
        }
        // 最后交换基准元素到重合点，此时右指针一定是小于基准元素的，左指针一定是大于基准元素的
        int temp = nums[start];
        nums[start] = nums[right];
        nums[right] = temp;
        return right;
    }

    private int singlePartition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start; // i 指向小于基准的最后一个元素的下一个位置
        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                if (i != j) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                i++;
            }
        }
        if (i != end) {
            int temp = nums[i];
            nums[i] = nums[end];
            nums[end] = temp;
        }
        return i;
    }

    private int partitionOpt(int[] nums, int low, int high) {

        // 优化：三数字交换法
        // 1. 选取中间值作为基准值
        int mid = low + (high - low) / 2;
        // 2. 交换三个数，使得 low < mid < high
        if (nums[low] > nums[mid]) ArrayUtil.swap(nums, low, mid);
        if (nums[low] > nums[high]) ArrayUtil.swap(nums, low, high);
        if (nums[mid] > nums[high]) ArrayUtil.swap(nums, mid, high);
        // 3. 选择中间数作为基准值，并移动到末尾
        ArrayUtil.swap(nums, mid, high);

        int pivot = nums[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (nums[j] <= pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[high];
        nums[high] = temp;
        return i + 1;
    }
}
