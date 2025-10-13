package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/koko-eating-bananas/description/
 * Video Explanation : https://www.youtube.com/watch?v=QQcEIxK-snE
 *
 * 875. Koko Eating Bananas
 * Medium
 * Topics
 *
 * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas
 * and eats k bananas from that pile. If the pile has less than k bananas,
 * she eats all of them instead and will not eat any more bananas during this hour.
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Constraints:
 *     1 <= piles.length <= 104
 *     piles.length <= h <= 109
 *     1 <= piles[i] <= 109
 *
 */
public class _15_KokoEatingBananas {

    private boolean canEatAllWithinHTime(int[] piles,int eatsPerHour,int availableTime){
        long totalHours=0;
        for (int i = 0; i < piles.length; i++) {
            int p = piles[i];
            long hours = (long) Math.ceil((double) p/eatsPerHour);
            totalHours+=hours;
            if (totalHours>availableTime) return false;
        }
        return true;
    }

    /**
     *
     * Time Complexity: O(n × log m)
     * - Finding max pile: O(n) - one pass through array
     * - Binary search loop: O(log m) iterations, where m = max pile size
     * - Validation per iteration: O(n) - checks all piles (with early exit optimization)
     * - Total: O(n) + O(n × log m) = O(n × log m) (dominant term)
     *
     * Space Complexity: O(1)
     * - Only constant variables: min, max, k, possibleAns, totalHours, hours
     * - No data structures scaling with input
    * */
    public int minEatingSpeed(int[] piles, int h) {
        int min=1;
        int max=piles[0];
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max,piles[i]);
        }

        int possibleAns=0;
        while (min<=max){
            int k = (min+max)/2;
            boolean canFinish = canEatAllWithinHTime(piles,k,h);
            if (canFinish){
                possibleAns = k;
                max = k-1;
            }else {
                min = k+1;
            }
        }

        return possibleAns;
    }

    public static void main(String[] args) {

        _15_KokoEatingBananas obj = new _15_KokoEatingBananas();

        int[] piles = {3,6,7,11};
        int h = 8;
        System.out.println("Possible K : "+obj.minEatingSpeed(piles,h));

        int[] piles1 = {30,11,23,4,20};
        int h1 = 5;
        System.out.println("Possible K : "+obj.minEatingSpeed(piles1,h1));

        int[] piles2 = {312884470};
        int h2 = 312884469;
        System.out.println("Possible K : "+obj.minEatingSpeed(piles2,h2));

        int[] piles3 = {805306368,805306368,805306368};
        int h3 = 1000000000;
        System.out.println("Possible K : "+obj.minEatingSpeed(piles3,h3));

    }
}