package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/minimum-number-of-days-to-make-m-bouquets/description/
 * Video Explanation : https://www.youtube.com/watch?v=erWfh_hBF80
 *
 * 1482. Minimum Number of Days to Make m Bouquets
 * Medium
 * Topics
 *
 * You are given an integer array bloomDay, an integer m and an integer k.
 * You want to make m bouquets. To make a bouquet, you need to use k adjacent flowers from the garden.
 * The garden consists of n flowers, the ith flower will bloom in the bloomDay[i] and
 * then can be used in exactly one bouquet.
 * Return the minimum number of days you need to wait to be able to make m bouquets from the garden.
 * If it is impossible to make m bouquets return -1.
 *
 * Example 1:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 1
 * Output: 3
 * Explanation: Let us see what happened in the first three days. x means flower bloomed and _ means flower did not bloom in the garden.
 * We need 3 bouquets each should contain 1 flower.
 * After day 1: [x, _, _, _, _]   // we can only make one bouquet.
 * After day 2: [x, _, _, _, x]   // we can only make two bouquets.
 * After day 3: [x, _, x, _, x]   // we can make 3 bouquets. The answer is 3.
 *
 * Example 2:
 * Input: bloomDay = [1,10,3,10,2], m = 3, k = 2
 * Output: -1
 * Explanation: We need 3 bouquets each has 2 flowers, that means we need 6 flowers. We only have 5 flowers so it is impossible to get the needed bouquets and we return -1.
 *
 * Example 3:
 * Input: bloomDay = [7,7,7,7,12,7,7], m = 2, k = 3
 * Output: 12
 * Explanation: We need 2 bouquets each should have 3 flowers.
 * Here is the garden after the 7 and 12 days:
 * After day 7: [x, x, x, x, _, x, x]
 * We can make one bouquet of the first three flowers that bloomed.
 * We cannot make another bouquet from the last three flowers that bloomed because they are not adjacent.
 * After day 12: [x, x, x, x, x, x, x]
 * It is obvious that we can make two bouquets in different ways.
 *
 * Constraints:
 *     bloomDay.length == n
 *     1 <= n <= 105
 *     1 <= bloomDay[i] <= 109
 *     1 <= m <= 106
 *     1 <= k <= n
 */
public class _16_MinNumberOfDaysToMakeMBouquets {

    private boolean canMakeBouquets(int[] bloomDay,int m,int k,int currentDay){
        int totalBouquets=0;
        int flowers=0;
        for (int i = 0; i < bloomDay.length; i++) {
            boolean hasBloomed = currentDay>=bloomDay[i];
            if (hasBloomed) {
                flowers++;
                if (flowers==k){ // one bouquet is done.
                    totalBouquets++;
                    flowers=0; // need to crete the next bouquet so lets start picking flowers from 0 again.
                }
            } else {
                flowers=0;
            }

            if (totalBouquets==m) return true;
        }
        return false;
    }

    /**
     * Solution 1 : Brute Force
     * Time Complexity: O(n × d)
     * d = range of days (maxDays - minDays + 1)
     * n = length of bloomDay array
     * The outer loop iterates through each day in range [minDays, maxDays]
     * For each day, canMakeBouquets() scans the entire array once: O(n)
     * Worst case: d can be up to 10^9, making this approach very inefficient
     *
     * Space Complexity: O(1)
     * Only uses a constant amount of extra variables
     * No additional data structures scale with input size
    * */
    public int minDays(int[] bloomDay, int m, int k) {

        // no point checking as we do not have sufficient no of flowers.
        if (m*k>bloomDay.length) return -1;

        int minDays = bloomDay[0];
        int maxDays = bloomDay[0];
        for (int i = 0; i < bloomDay.length; i++) {
            int day = bloomDay[i];
            minDays = Math.min(minDays,day);
            maxDays = Math.max(maxDays,day);
        }

        for (int i = minDays; i <= maxDays; i++) {
            // let's go one day at a time and check if we can complete our bouquets
            if (canMakeBouquets(bloomDay, m,k, i)) return i;
        }

        return -1;
    }

    /**
     * Solution 2 :
     *
     * Time Complexity: O(n × log d)
     * n = length of bloomDay array
     * d = range of days (maxDays - minDays)
     * Binary search runs in O(log d) iterations
     * Each iteration calls canMakeBouquets() which scans the array once: O(n)
     * Total: O(n × log d)
     *
     * Space Complexity: O(1)
     * Only uses a constant amount of extra variables
     * No additional data structures scale with input size
     *
    * */
    public int minDays1(int[] bloomDay, int m, int k) {

        // no point checking as we do not have sufficient no of flowers.
        if ((long)m * k > bloomDay.length) return -1;

        int minDays = bloomDay[0];
        int maxDays = bloomDay[0];
        for (int i = 0; i < bloomDay.length; i++) {
            int day = bloomDay[i];
            minDays = Math.min(minDays,day);
            maxDays = Math.max(maxDays,day);
        }

        while (minDays<maxDays){
            int mid = (minDays+maxDays)/2;
            if (canMakeBouquets(bloomDay,m,k,mid)){
                // we can create m bouquets however let's keep checking if we can complete the
                // bouquet in fewer days.
                maxDays=mid;
            }else {
                minDays=mid+1;
            }
        }

        return maxDays;
    }

    public static void main(String[] args) {

        _16_MinNumberOfDaysToMakeMBouquets obj = new _16_MinNumberOfDaysToMakeMBouquets();

        int[] piles = {1,10,3,10,2};
        int m = 3;
        int k = 1;
        System.out.println("Possible K : "+obj.minDays(piles,m,k));
        System.out.println("Possible K : "+obj.minDays1(piles,m,k));

        int[] piles1 = {1,10,3,10,2};
        int m1 = 3;
        int k1 = 2;
        System.out.println("Possible K : "+obj.minDays(piles1,m1,k1));
        System.out.println("Possible K : "+obj.minDays1(piles1,m1,k1));

        int[] piles2 = {7,7,7,7,12,7,7};
        int m2 = 2;
        int k2 = 3;
        System.out.println("Possible K : "+obj.minDays(piles2,m2,k2));
        System.out.println("Possible K : "+obj.minDays1(piles2,m2,k2));

    }
}