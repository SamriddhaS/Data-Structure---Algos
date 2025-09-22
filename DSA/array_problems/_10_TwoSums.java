package array_problems;


import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * Question link : https://leetcode.com/problems/two-sum/
 * Video Explenation :
 *
 *
 *  1. Two Sum
 *
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 *
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 *
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 *
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 *
 * Constraints:
 *
 *     2 <= nums.length <= 104
 *     -109 <= nums[i] <= 109
 *     -109 <= target <= 109
 *     Only one valid answer exists.
 *
 * Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
 *
 *
* */
public class _10_TwoSums {


    /**
    *
     * Time Complexity: O(n²)
     *
     * Nested loops: outer loop runs n times, inner loop runs up to (n-1) times
     * Worst case: checks all possible pairs = n(n-1)/2 comparisons
     *
     * Space Complexity: O(1)
     *
     * Only uses a few variables (i, j, currentSum, ans)
     * No additional data structures that scale with input size
     *
    * */
    public int[] twoSumBrute(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[0]; // Empty array (though we'll never return this)

        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n) {
                int currentSum = nums[i] + nums[j];
                if (currentSum == target) {
                    return new int[]{i, j};
                }
                j++;
            }
        }
        return ans;
    }

    /**
    * Time Complexity: O(n log n)
     *
     * Arrays.sort(copy): O(n log n)
     * Two-pointer while loop: O(n)
     * Final index mapping loop: O(n)
     * Overall: O(n log n) + O(n) + O(n) = O(n log n)
     *
     * Space Complexity: O(n)
     *
     * nums.clone(): O(n) for the copied array
     * Additional arrays (values, answer): O(1)
     * Overall: O(n)
    * */
    public int[] twoSumBetter(int[] nums, int target) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int i = 0;
        int j = nums.length-1;
        int[] values=new int[2];
        int[] answer=new int[2];
        while(i<j){
            if(copy[i]+copy[j]>target){
                j--;
            }else if(copy[i]+copy[j]<target){
                i++;
            }else{
                values[0]=copy[i];
                values[1]=copy[j];
                break;
            }
        }
        boolean val1=false;
        boolean val2=false;
        for(int f=0;f<nums.length;f++){
            if(values[0]==nums[f]&&!val1){
                answer[0]=f;
                val1=true;
            } else if(values[1]==nums[f]&&!val2){
                answer[1]=f;
                val2=true;
            }
            if(val1&&val2){
                return answer;
            }
        }
        return answer;
    }

    /**
     * Time Complexity: O(n)
     *
     * Single for loop: runs n times
     * HashMap operations (containsKey, get, put): O(1) average case
     * Overall: O(n) × O(1) = O(n)
     *

     * Space Complexity: O(n)
     *
     * HashMap stores up to n key-value pairs in worst case
     * Each entry: one integer key + one integer value
     * Overall: O(n)
    * */
    public int[] twoSumBestInTimeComplexity(int[] nums, int target) {
        HashMap<Integer,Integer> indexMap = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            int required = target-nums[i];
            if (indexMap.containsKey(required)){
                return new int[] {i,indexMap.get(required)};
            }
            indexMap.put(nums[i],i );
        }

        return new int[0];
    }

    public static void main(String[] args) {

        _10_TwoSums solution = new _10_TwoSums();

        int[] inputArr = new int[]{2,7,11,15};
        int[] result = solution.twoSumBestInTimeComplexity(inputArr,9);
        for (int i:result){
            System.out.println(i);
        }

    }
}
