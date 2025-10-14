package binary_search;

import java.util.Arrays;

/**
 * Problem Link : https://www.naukri.com/code360/problems/aggressive-cows_1082559
 * https://takeuforward.org/data-structure/aggressive-cows-detailed-solution/
 * Video Explanation : https://www.youtube.com/watch?v=R_Mfw4ew-Vo
 *
 * Aggressive Cows : Detailed Solution
 *
 * Problem Statement: You are given an array 'arr' of size 'n' which denotes the position of stalls.
 * You are also given an integer 'k' which denotes the number of aggressive cows.
 * You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.
 * Find the maximum possible minimum distance.
 *
 * Example 1:
 * Input Format: N = 6, k = 4, arr[] = {0,3,4,7,10,9}
 * Result: 3
 * Explanation: The maximum possible minimum distance between any two cows will be 3 when 4 cows
 * are placed at positions {0, 3, 7, 10}. Here the distances between cows are 3, 4, and 3 respectively.
 * We cannot make the minimum distance greater than 3 in any ways.
 *
 * Example 2:
 * Input Format: N = 5, k = 2, arr[] = {4,2,1,3,6}
 * Result: 5
 * Explanation: The maximum possible minimum distance between any two cows will be 5 when 2 cows are
 * placed at positions {1, 6}.
 *
 */
public class _20_AggressiveCows {

    private boolean canPlaceCows(int[] sortedStalls,int cows,int distance){
        int preStall=sortedStalls[0];
        cows--;
        for (int i = 1; i < sortedStalls.length; i++) {
            if (sortedStalls[i]-preStall>=distance){
                cows--;
                preStall = sortedStalls[i];
            }
            if(cows==0) return true;
        }
        return cows==0;
    }

    /**
     * Time Complexity: O(n log n + n × maxDistance)
     * - Arrays.sort(stalls): O(n log n)
     * - Loop from maxPossibleDistance down to 1: O(maxDistance) iterations
     * - maxDistance = stalls[n-1] - stalls[0] (could be very large, e.g., 10^9
     * - Each iteration calls canPlaceCows(): O(n)
     * - Overall: O(n log n + n × maxDistance)
     *
     * Space Complexity: O(1) or O(log n)
     * - O(1) auxiliary space (ignoring sorting)
     * - O(log n) if counting sorting's recursion stack
    * */
    public int findMinimumDistance(int[] stalls,int cows){
        Arrays.sort(stalls);
        int maxPossibleDistance=stalls[stalls.length-1]-stalls[0];
        for (int i = maxPossibleDistance; i >= 1; i--) {
            if (canPlaceCows(stalls,cows,i)) return i;
        }
        return 0;
    }

    /**
     *
     * Time Complexity: O(n log n + n × log(maxDistance))
     * - Arrays.sort(stalls): O(n log n)
     * - Binary search on distance: O(log(maxDistance)) iterations
     * - Search space: [1, maxDistance]
     * - Each iteration calls canPlaceCows(): O(n)
     * Overall: O(n log n + n × log(maxDistance))
     *
     * Space Complexity: O(1) or O(log n)
     * - O(1) auxiliary space
     * - O(log n) for sorting's recursion stack
    * */
    public int findMinimumDistance1(int[] stalls,int cows){
        Arrays.sort(stalls);
        int max=stalls[stalls.length-1]-stalls[0];
        int min=1;
        int ans=0;
        while(min<=max){
            int distance = (min+max)/2;
            if (canPlaceCows(stalls,cows,distance)){
                ans=distance; // save the possible distance for answer.
                min=distance+1; // Try for larger distance
            }else {
                max=distance-1; // try smaller distance
            }
        }

        return ans;
    }


    public static void main(String[] args) {

        _20_AggressiveCows obj = new _20_AggressiveCows();

        // Test Case 1: Original example
        int[] stalls1 = {1,2,3};
        int k1 = 2;
        System.out.println("Test 1 - Expected: 3, Got: " + obj.findMinimumDistance(stalls1, k1));
        System.out.println("Test 1 - Expected: 3, Got: " + obj.findMinimumDistance1(stalls1, k1));

        // Test Case 2: Original example
        int[] stalls2 = {4,2,1,3,6};
        int k2 = 2;
        System.out.println("Test 2 - Expected: 5, Got: " + obj.findMinimumDistance(stalls2, k2));
        System.out.println("Test 2 - Expected: 5, Got: " + obj.findMinimumDistance1(stalls2, k2));

        // Test Case 3: Minimum cows (k=2) with evenly spaced stalls
        int[] stalls3 = {1,2,4,8,9};
        int k3 = 3;
        System.out.println("Test 3 - Expected: 3, Got: " + obj.findMinimumDistance(stalls3, k3));
        System.out.println("Test 3 - Expected: 3, Got: " + obj.findMinimumDistance1(stalls3, k3));
        // Explanation: Place cows at positions {1, 4, 8} -> distances are 3, 4

        // Test Case 4: All cows in all stalls (k = n)
        int[] stalls4 = {1,2,8,4,9};
        int k4 = 5;
        System.out.println("Test 4 - Expected: 1, Got: " + obj.findMinimumDistance(stalls4, k4));
        System.out.println("Test 4 - Expected: 1, Got: " + obj.findMinimumDistance1(stalls4, k4));
        // Explanation: All stalls must be used, minimum distance is 1 (between positions 1 and 2)

        // Test Case 5: Large gaps between stalls
        int[] stalls5 = {1,2,100,101,200,201};
        int k5 = 3;
        System.out.println("Test 5 - Expected: 100, Got: " + obj.findMinimumDistance(stalls5, k5));
        System.out.println("Test 5 - Expected: 100, Got: " + obj.findMinimumDistance1(stalls5, k5));
        // Explanation: Place cows at {1, 100, 200} -> distances are 99, 100

        // Test Case 6: Only 2 cows with multiple stalls
        int[] stalls6 = {5,10,15,20,25,30};
        int k6 = 2;
        System.out.println("Test 6 - Expected: 25, Got: " + obj.findMinimumDistance(stalls6, k6));
        System.out.println("Test 6 - Expected: 25, Got: " + obj.findMinimumDistance1(stalls6, k6));
        // Explanation: Place cows at {5, 30} for maximum distance of 25

        // Test Case 7: Consecutive positions
        int[] stalls7 = {10,20,30,40,50};
        int k7 = 3;
        System.out.println("Test 7 - Expected: 20, Got: " + obj.findMinimumDistance(stalls7, k7));
        System.out.println("Test 7 - Expected: 20, Got: " + obj.findMinimumDistance1(stalls7, k7));
        // Explanation: Place cows at {10, 30, 50} -> distances are 20, 20

    }
}