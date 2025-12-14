package array_problems;

import java.util.*;

/**
*
 * Leetcode Link : https://leetcode.com/problems/3sum/description/?envType=problem-list-v2&envId=sorting
 * Explanation : https://www.youtube.com/watch?v=DhFh8Kw7ymk
 *
 * 15. 3Sum
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2].
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 * Example 2:
 *
 * Input: nums = [0,1,1]
 * Output: []
 * Explanation: The only possible triplet does not sum up to 0.
 *
 * Example 3:
 *
 * Input: nums = [0,0,0]
 * Output: [[0,0,0]]
 * Explanation: The only possible triplet sums up to 0.
 *
 *
 *
 * Constraints:
 *
 *     3 <= nums.length <= 3000
 *     -105 <= nums[i] <= 105
 *
* */
class Solution {

    /**
    *  With O(n3) time complexity. As we are running 3 for loops to solve the problem.
     * Time complexity : O(n3) + nLogN (for set)
    * */
    public List<List<Integer>> threeSumBruteForce(int[] nums) {
        int target = 0;
        Set<ArrayList<Integer>> answers = new HashSet<>();
        for(int i=0;i< nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                for(int k=j+1;k< nums.length;k++){
                    if(nums[i]+nums[j]+nums[k]==target){
                        ArrayList<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        Collections.sort(temp);
                        answers.add(temp);
                    }
                }
            }
        }

        return new ArrayList<>(answers);
    }

    /**
     *
     * Better -->
     *
     * Here we are able to remove the third loop by using set data structure.
     * But still we are using extra space by using extra set<vector<int>> output; this variable.
     * Probably we can optimise this by not using the extra space set takes.
     *
     * TC -> O(n2) + O(logN)
     * SC -> O(n) for the Hashset inside the loop + O(nLogN) * 2 (Due to set<List<Int>>)
    * */
    public List<List<Integer>> threeSumBetterSolution(int[] nums) {
        int target = 0;
        Set<ArrayList<Integer>> answers = new HashSet<>();
        for(int i=0;i< nums.length;i++){
            HashSet<Integer> elements = new HashSet<>();
            for(int j=i+1;j< nums.length;j++){
                Integer target1 = target-(nums[i]+nums[j]) ;
                if(elements.contains(target1)){
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(target1);
                    Collections.sort(temp);
                    answers.add(temp);
                }
                elements.add(nums[j]);
            }
        }

        return new ArrayList<>(answers);
    }

    /**
     * Optimal Solution -->
     * 2 ponter approach aling with sorting the list at first.
     * Explenation video link : https://www.youtube.com/watch?v=DhFh8Kw7ymk
     *
     * Basically we sort the list first then. Iterate over the whole loop.
     * For every iteration we do a 2pointers search for the rest of the elements in the list.
     *
     * Time complexity :
     *
     * nLogN (Sorting the array) + O(n) (iterating through the list) * O (n) (while loop runs from n-1,n-2,n-3 times...)
     * = nLogN + o(n2)
     *
     * Space complexity : O(number of unique triplets) - We get rid of the external DS that is hashmap.
    * */
    public List<List<Integer>> threeSumOptimalSpaceSolution(int[] nums) {
        int target = 0;
        ArrayList<ArrayList<Integer>> answers = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i< nums.length;i++){
            if (i>0 && nums[i]==nums[i-1]) continue;
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                Integer sum = nums[i]+nums[j]+nums[k];
                if (sum>target){
                    k--;
                } else if(sum<target){
                    j++;
                }else {
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    answers.add(temp);
                    j++;
                    k--;
                    while(j<k && nums[j]==nums[j-1]) j++;
                    while(j<k && nums[k]==nums[k+1]) k--;
                }
            }
        }
        return new ArrayList<>(answers);
    }

    /**
    * Revisited 14th December 2025
    * */

    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Example from the problem description
        // Expected output: [[-1, -1, 2], [-1, 0, 1]] (order does not matter)
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println("Test Case 1: Input = " + Arrays.toString(nums1));
        List<List<Integer>> result1 = solution.threeSumBruteForce(nums1);
        System.out.println("Output: " + formatResult(result1));
        System.out.println("----------------------------------------");

        // Test Case 2: Example with no solution
        // Expected output: []
        int[] nums2 = {0, 1, 1};
        System.out.println("Test Case 2: Input = " + Arrays.toString(nums2));
        List<List<Integer>> result2 = solution.threeSumBruteForce(nums2);
        System.out.println("Output: " + formatResult(result2));
        System.out.println("----------------------------------------");

        // Test Case 3: Example with all zeros
        // Expected output: [[0, 0, 0]]
        int[] nums3 = {0, 0, 0};
        System.out.println("Test Case 3: Input = " + Arrays.toString(nums3));
        List<List<Integer>> result3 = solution.threeSumBruteForce(nums3);
        System.out.println("Output: " + formatResult(result3));
        System.out.println("----------------------------------------");

        // Test Case 4: A single valid triplet with duplicates
        // Expected output: [[-2, 0, 2]]
        int[] nums4 = {-2, 0, 0, 2, 2};
        System.out.println("Test Case 4: Input = " + Arrays.toString(nums4));
        List<List<Integer>> result4 = solution.threeSumBruteForce(nums4);
        System.out.println("Output: " + formatResult(result4));
        System.out.println("----------------------------------------");

    }

    /**
     * Helper method to sort the inner lists and the outer list for consistent
     * output formatting, which makes it easier to compare with expected results.
     */
    private static String formatResult(List<List<Integer>> result) {
        // Sort each inner list
        for (List<Integer> triplet : result) {
            Collections.sort(triplet);
        }
        // Sort the outer list based on the first element of each inner list
        result.sort((a, b) -> {
            if (!a.isEmpty() && !b.isEmpty()) {
                return a.get(0) - b.get(0);
            }
            return 0;
        });
        return result.toString();
    }
}

