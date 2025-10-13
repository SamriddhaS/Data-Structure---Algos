package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 * Video Explanation : https://www.youtube.com/watch?v=ER_oLmdc-nw
 *
 * @see _16_MinNumberOfDaysToMakeMBouquets similar problem.
 * @see _15_KokoEatingBananas similar problem.
 * @see _17_FindSmallestDivisorGivenAThreshold similar problem.
 *
 * 1011. Capacity To Ship Packages Within D Days
 * Solved
 * Medium
 * Topics
 *
 * A conveyor belt has packages that must be shipped from one port to another within days.
 * The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
 * Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days.
 *
 * Example 1:
 *
 * Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
 * Output: 15
 * Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
 * 1st day: 1, 2, 3, 4, 5
 * 2nd day: 6, 7
 * 3rd day: 8
 * 4th day: 9
 * 5th day: 10
 *
 * Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.
 *
 * Example 2:
 * Input: weights = [3,2,2,4,1,4], days = 3
 * Output: 6
 * Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
 * 1st day: 3, 2
 * 2nd day: 2, 4
 * 3rd day: 1, 4
 *
 * Example 3:
 * Input: weights = [1,2,3,1,1], days = 4
 * Output: 3
 * Explanation:
 * 1st day: 1
 * 2nd day: 2
 * 3rd day: 3
 * 4th day: 1, 1
 *
 * Constraints:
 *     1 <= days <= weights.length <= 5 * 104
 *     1 <= weights[i] <= 500
 *
 */
public class _18_CapacityToShipPakagesWithinDDays {


    /**
     * Why did I make this function this complicated ?
     * Because I didn't consider the fact that we are having min capacity value ( = maxOf(weights) ).
     * So capacity will never overflow beyond the
     * @param capacity  value.
     * Due to this fact I wrong the function to work with any capacity value.
     * So even if we pass a capacity value that is lesser than the maxOf(weights) value it will still work.
    * */
    private int neededDays(int[] weights,int capacity){
        int totalDays=0;
        int carrayWeight=0;
        for (int weight:weights){
            if (carrayWeight+weight<capacity){
                // not exceeding days capacity can add more weight.
                carrayWeight+=weight;
            } else if (carrayWeight+weight==capacity){
                // max capacity reached can't add any more wright today.
                totalDays++;
                carrayWeight=0;
            } else {
                // Exceeding today's capacity cant pick this weight. So pick it next day,
                if (carrayWeight>0){
                    totalDays++;
                    carrayWeight=0;
                }
                if (weight>=capacity) {
                    totalDays+= (int) Math.ceil((double) weight/capacity);
                }else{
                    carrayWeight+=weight;
                }

            }
        }
        if (carrayWeight>0) totalDays++;
        return totalDays;
    }

    /**
     * Solution 1 : Brute Force
     *
     * Time Complexity: O(n × (sum - max))
     * Outer loop iterates from minCapacity to maxCapacity: O(sum - max) iterations
     * Each iteration calls neededDays(): O(n) to traverse all weights
     * Overall: O(n × (sum - max)), where n = array length, sum = total weight, max = heaviest package
     *
     * Space Complexity: O(1)
     * Only uses constant extra space for variables
     *
    * */
    public int shipWithinDays(int[] weights, int days) {
        int minCapacity=weights[0];
        int maxCapacity=0;
        for (int i = 0; i < weights.length; i++) {
            minCapacity = Math.max(minCapacity,weights[i]);
            maxCapacity+=weights[i];
        }
        for (int currCap = minCapacity; currCap <= maxCapacity; currCap++) {
            int reqDays = neededDays(weights, currCap);
            if (reqDays<=days) return currCap;
        }
        return -1;
    }

    /**
     * Much simpler and easier to read.
     * @param capacity is in the range > maxOf(weights) value.
     * We are having min capacity value ( = maxOf(weights) ).
     * So capacity will never go to <0, or it will never be a negative value.
     * Node this function will not work if you send capacity value < maxOf(weights).
     * */
    private int neededDays1(int[] weights,int capacity){
        int totalDays=1;
        int currentCapacity=capacity;
        for (int weight:weights){
            if (currentCapacity-weight<0){
                totalDays++;
                currentCapacity=capacity;
            }
            currentCapacity-=weight;
        }
        return totalDays;
    }

    /**
     * Solution 1 : Optimal
     *
     * Time Complexity: O(n × log(sum - max))
     * - Binary search on capacity range: O(log(sum - max)) iterations
     * - Each iteration calls neededDays(): O(n) to traverse all weights
     * - Overall: O(n × log(sum - max))
     *
     * Space Complexity: O(1)
     * - Only uses constant extra space for variables
    * */
    public int shipWithinDays1(int[] weights, int days) {
        int minCapacity=weights[0];
        int maxCapacity=0;
        for (int i = 0; i < weights.length; i++) {
            minCapacity = Math.max(minCapacity,weights[i]);
            maxCapacity+=weights[i];
        }
        while (minCapacity<=maxCapacity){
            int currCap = (minCapacity+maxCapacity)/2;
            int reqDays = neededDays1(weights, currCap);
            if (reqDays<=days){
                maxCapacity = currCap-1;
            }else {
                minCapacity = currCap+1;
            }
        }

        return minCapacity;
    }

    public static void main(String[] args) {

        _18_CapacityToShipPakagesWithinDDays obj = new _18_CapacityToShipPakagesWithinDDays();

        int[] piles = {1,2,3,4,5,6,7,8,9,10};
        int maxDays = 5;
        System.out.println("Possible K : "+obj.shipWithinDays(piles,maxDays));
        System.out.println("Possible K : "+obj.shipWithinDays1(piles,maxDays));

        int[] piles1 = {3,2,2,4,1,4};
        int maxDays1 = 3;
        System.out.println("Possible K : "+obj.shipWithinDays(piles1,maxDays1));
        System.out.println("Possible K : "+obj.shipWithinDays1(piles1,maxDays1));

        int[] piles2 = {1,2,3,1,1};
        int maxDays2 = 4;
        System.out.println("Possible K : "+obj.shipWithinDays(piles2,maxDays2));
        System.out.println("Possible K : "+obj.shipWithinDays1(piles2,maxDays2));

    }
}