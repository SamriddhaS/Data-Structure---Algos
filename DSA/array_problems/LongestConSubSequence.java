package DSA.array_problems;

import java.util.Arrays;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConSubSequence {

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int currentLongestSeq = 1;
        int tempSeq = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                continue;
            } else if (nums[i] - nums[i - 1] == 1) {
                tempSeq++;
            } else {
                currentLongestSeq = Math.max(currentLongestSeq, tempSeq);
                tempSeq = 1;
            }
        }
        return Math.max(currentLongestSeq, tempSeq);
    }

    public int longestConsecutiveUsingHasSet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        HashSet<Integer> mySet = new HashSet<>();
        for (int i=0;i<nums.length;i++){
            mySet.add(nums[i]);
        }
        int result = 1;
        for(int i:mySet){
            if (mySet.contains(i-1)) continue;
            else {
                int seq=1;
                int number=i;
                while (mySet.contains(number+1)){
                    seq++;
                    number++;
                }
                if (seq>result) result=seq;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LongestConSubSequence solution = new LongestConSubSequence();

        // Test cases
        int[] test1 = {100, 4, 200, 1, 3, 2};
        long startTime1 = System.nanoTime();
        int result1 = solution.longestConsecutive(test1);
        long endTime1 = System.nanoTime();
        double duration1 = (double)(endTime1 - startTime1) / 1_000_000.0;
        System.out.println("Input: " + Arrays.toString(test1));
        System.out.println("Longest consecutive sequence length: " + result1);
        System.out.printf("Time taken: %.4f milliseconds%n", duration1);
        System.out.println("---");

        int[] test2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        long startTime2 = System.nanoTime();
        int result2 = solution.longestConsecutive(test2);
        long endTime2 = System.nanoTime();
        double duration2 = (double)(endTime2 - startTime2) / 1_000_000.0;
        System.out.println("Input: " + Arrays.toString(test2));
        System.out.println("Longest consecutive sequence length: " + result2);
        System.out.printf("Time taken: %.4f milliseconds%n", duration2);
        System.out.println("---");

        int[] test3 = {};
        long startTime3 = System.nanoTime();
        int result3 = solution.longestConsecutive(test3);
        long endTime3 = System.nanoTime();
        double duration3 = (double)(endTime3 - startTime3) / 1_000_000.0;
        System.out.println("Input: " + Arrays.toString(test3));
        System.out.println("Longest consecutive sequence length: " + result3);
        System.out.printf("Time taken: %.4f milliseconds%n", duration3);
        System.out.println("---");

        int[] test4 = {1, 2, 0, 1};
        long startTime4 = System.nanoTime();
        int result4 = solution.longestConsecutive(test4);
        long endTime4 = System.nanoTime();
        double duration4 = (double)(endTime4 - startTime4) / 1_000_000.0;
        System.out.println("Input: " + Arrays.toString(test4));
        System.out.println("Longest consecutive sequence length: " + result4);
        System.out.printf("Time taken: %.4f milliseconds%n", duration4);
        System.out.println("---");
    }
}