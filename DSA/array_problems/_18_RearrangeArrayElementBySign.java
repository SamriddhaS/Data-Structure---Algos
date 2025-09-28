package array_problems;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem Link : https://leetcode.com/problems/rearrange-array-elements-by-sign/description/
 * Video :
 * Topic : TArrays
 *
 * 2149. Rearrange Array Elements by Sign
 * Medium
 * Topics
 *
 * You are given a 0-indexed integer array nums of even length consisting of an
 * equal number of positive and negative integers.
 *
 * You should return the array of nums such that the the array follows the given conditions:
 *
 *     Every consecutive pair of integers have opposite signs.
 *     For all integers with the same sign, the order in which they were present in nums is preserved.
 *     The rearranged array begins with a positive integer.
 *
 * Return the modified array after rearranging the elements to satisfy the aforementioned conditions.
 *
 * Example 1:
 * Input: nums = [3,1,-2,-5,2,-4]
 * Output: [3,-2,1,-5,2,-4]
 * Explanation:
 * The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
 * The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
 * Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.
 *
 * Example 2:
 * Input: nums = [-1,1]
 * Output: [1,-1]
 * Explanation:
 * 1 is the only positive integer and -1 the only negative integer in nums.
 * So nums is rearranged to [1,-1].
 *
 * Constraints:
 *
 *     2 <= nums.length <= 2 * 105
 *     nums.length is even
 *     1 <= |nums[i]| <= 105
 *     nums consists of equal number of positive and negative integers.
 *
 * It is not required to do the modifications in-place.
 *
* */
class _18_RearrangeArrayElementBySign {

    /**
     *
     * Time Complexity: O(n)
     * - First loop: O(n) to separate positive/negative numbers
     * - Second loop: O(n/2) to fill the answer array
     * Total: O(n)
     *
     * Space Complexity: O(n)
     * - answer array: O(n)
     * - positive ArrayList: O(n/2)
     * - negative ArrayList: O(n/2)
     * Total: O(n)
    * */
    public int[] rearrangeArray(int[] nums) {
        int[] answer = new int[nums.length];
        ArrayList<Integer> positive = new ArrayList<>();
        ArrayList<Integer> negative = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i]>0) positive.add(nums[i]);
            else negative.add(nums[i]);
        }

        int index=-1;
        for (int i = 0; i < positive.size(); i++) {
            answer[++index] = positive.get(i);
            answer[++index] = negative.get(i);
        }

        return answer;
    }

    /**
    * Time Complexity: O(n)
     *
     * Single loop through the array: O(n)
     * Each element is processed exactly once
     * Total: O(n)
     *
     * Space Complexity: O(1)
     *
     * Only uses a few extra variables: positivePointer, negativePointer, ele, i
     * The answer array is the required output, not considered "extra" space
     * Total: O(1) extra space
     *
     * Note: If we count the output array, it would be O(n) total space,
     * but typically in complexity analysis, the space required for the
     * output is not counted as "extra" space since it's mandated by the problem requirements.
     * Also, its mentioned in the question "It is not required to do the modifications in-place."
    * */
    public int[] rearrangeArray1(int[] nums) {
        int[] answer = new int[nums.length];
        int positivePointer=0;
        int negativePointer=1;
        for (int i = 0; i < nums.length; i++) {
            int ele = nums[i];
            if (ele>0){
                answer[positivePointer] = ele;
                positivePointer+=2;
            }else {
                answer[negativePointer] = ele;
                negativePointer+=2;
            }
        }
        return answer;
    }

    public static void main(String[] args) {

        _18_RearrangeArrayElementBySign solution = new _18_RearrangeArrayElementBySign();

        int[] input = {3,1,-2,-5,2,-4};
        System.out.println("Answer : "+ Arrays.toString(solution.rearrangeArray(input)));
        System.out.println("Answer : "+ Arrays.toString(solution.rearrangeArray1(input)));

    }
}
