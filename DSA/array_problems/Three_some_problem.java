package DSA.array_problems;

import java.util.*;

class Solution {

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

