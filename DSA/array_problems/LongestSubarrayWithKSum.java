package DSA.array_problems;

import java.util.HashMap;

class Main {
    public int longestSubarrayBruteForce(int[] nums, int k) {
        int longestSubarray = 0;
        for(int i=0;i<nums.length;i++){
            int sum = 0;
            for(int j=i;j<nums.length;j++){
                sum = sum+nums[j];
                if(sum == k){
                    int currentSize=(j-i)+1;
                    if(currentSize>longestSubarray) longestSubarray = currentSize;
                }
            }
        }
        return longestSubarray;
    }

    public int longestSubarrayUsingHashMap(int[] nums, int k) {
        int longestSubarray = 0;
        HashMap<Integer,Integer> trackMap = new HashMap<>();
        trackMap.put(0,0);
        int sum = 0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            int diff = sum-k;
            if(trackMap.containsKey(diff)){
                int subArrayDis = (i+1)-trackMap.get(diff);
                if(subArrayDis>longestSubarray) longestSubarray = subArrayDis;
            }
            if (!trackMap.containsKey(sum)) trackMap.put(sum,i+1);
        }
        return longestSubarray;
    }

    public int longestSubArrayUsingSlidingWindow(int[] nums,int k){
        int longestSubarray = 0;
        int sum = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            // Shrink the window from the left if the sum exceeds k
            while (sum > k && left <= right) {
                sum -= nums[left];
                left++;
            }

            // If the current window's sum equals k, update the max length
            if (sum == k) {
                longestSubarray = Math.max(longestSubarray, right - left + 1);
            }
        }
        return longestSubarray;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        // Test Case 1: Standard case with a valid subarray
        int[] nums1 = {10, 5, 2, 7, 1, 9};
        int k1 = 15;
        System.out.println("Input: nums = [10, 5, 2, 7, 1, 9], k = 15");
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + solution.longestSubarrayBruteForce(nums1,
                k1)); // Expected output: 4
        System.out.println("Actual Output Using HashMap: " + solution.longestSubarrayUsingHashMap(nums1,
                k1)); // Expected output: 4
        System.out.println("Actual Output Using SlidingWindow: " + solution.longestSubArrayUsingSlidingWindow(nums1,
                k1)); // Expected output: 4
        System.out.println("---");
        // Test Case 2: No subarray sums to k
        int[] nums2 = {-3, 2, 1};
        int k2 = 6;
        System.out.println("Input: nums = [-3, 2, 1], k = 6");
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output: " + solution.longestSubarrayBruteForce(nums2,
                k2)); // Expected output: 0
        System.out.println("Actual Output Using HashMap: " + solution.longestSubarrayUsingHashMap(nums2,
                k2)); // Expected output: 4
        System.out.println("---");
    // Test Case 3: Empty array
        int[] nums3 = {};
        int k3 = 5;
        System.out.println("Input: nums = {}, k = 5");
        System.out.println("Expected Output: 0");
        System.out.println("Actual Output: " + solution.longestSubarrayBruteForce(nums3,
                k3)); // Expected output: 0
        System.out.println("Actual Output Using HashMap: " + solution.longestSubarrayUsingHashMap(nums3,
                k3)); // Expected output: 4
        System.out.println("---");

// Test Case 4: Array with negative numbers and zero
        int[] nums4 = {1, -1, 5, -2, 3};
        int k4 = 3;
        System.out.println("Input: nums = [1, -1, 5, -2, 3], k = 3");
        System.out.println("Expected Output: 4");
        System.out.println("Actual Output: " + solution.longestSubarrayBruteForce(nums4,
                k4)); // Expected output: 4 (for subarray [1, -1, 5, -2])
        System.out.println("Actual Output Using HashMap: " + solution.longestSubarrayUsingHashMap(nums4,
                k4)); // Expected output: 4
        System.out.println("---");
// Test Case 5: Single element array that equals k
        int[] nums5 = {10};
        int k5 = 10;
        System.out.println("Input: nums = [10], k = 10");
        System.out.println("Expected Output: 1");
        System.out.println("Actual Output: " + solution.longestSubarrayBruteForce(nums5,
                k5)); // Expected output: 1
        System.out.println("Actual Output Using HashMap: " + solution.longestSubarrayUsingHashMap(nums5,
                k5)); // Expected output: 4
        System.out.println("---");
    }
}