package com.example.algorithm.sort;

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
 * </pre>
 *
 * 快排是不稳定的排序算法，因为在排序的过程中，元素的相对位置会发生变化。
 * 例如 [5a, 3, 2, 5b, 1]，第一轮使用5a作为基准元素，排序后变为 [3, 2, 1, 5b, 5a]，5a和5b的相对位置发生了变化。
 *
 * @author zhangjw54
 */
public class QuickSort {

    int[] nums = {12, 4, 6, 8, 3, 1, 15, 7};

    @Test
    public void quickSort() {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        // 得到基准元素的位置，双边快排
        // int middle = partition(nums, start, end);

        // 单边快排
        int middle = singlePartition(nums, start, end);
        // 分别对两部分进行递归排序
        quickSort(nums, start, middle - 1);
        quickSort(nums, middle + 1, end);
    }

    private int partition(int[] nums, int start, int end) {
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
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        int temp = nums[i + 1];
        nums[i + 1] = nums[end];
        nums[end] = temp;
        return i + 1;
    }
}
