package array_problems;


/**
 *
 * Question link : https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 * Video Explanation : https://www.youtube.com/watch?v=excAOvwF_Wk
 *
 *
 * 121. Best Time to Buy and Sell Stock
 * Solved
 * Easy
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
 *
 *
 * Example 1:
 * Input: prices = [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 * Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 *
 * Example 2:
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transactions are done and the max profit = 0.
 *
 *
 * Constraints:
 *  1 <= prices.length <= 105
 *  0 <= prices[i] <= 104
 *
* */
public class _1_StockBuyAndSell {

    /**
     * ####### SOLUTION 1 ##############
     * This is the first solution I came up with. Basically we have theree variables to store the lowest, hightest and difference.
     * We loop throught the array and update the buyPrice variable if the current value is lower than the previous value stored in
     * this variable.
     * Similarly we update the sellingPrice variable if current value is larger than buying price and also larger than the
     * previously stored sellingPrice.
     * Then diffrence variable to store the difference between lowest and highest. We update the difference if current diff is larger
     * than the privious one.
     *
     * Time Complixity --> O(n)
     * Space Complexity --> O(1)
     * It took 1ms time and used 97mb memory. This can be better lets see if we can optimise the sapce and time somehow.
     *
     * class Solution {
     * public:
     *     int maxProfit(vector<int>& prices) {
     *         int buyPrice=-1;
     *         int sellingPrice=0;
     *         int diffrence = 0;
     *         for(int i=0;i<prices.size();i++){
     *             if(buyPrice==-1 || prices[i]<buyPrice
     *             && i!=prices.size()-1
     *             ){
     *                 buyPrice = prices[i];
     *                 sellingPrice=0;
     *             }
     *             if(prices[i]>sellingPrice && prices[i]>buyPrice){
     *                 sellingPrice = prices[i];
     *             }
     *             if((sellingPrice-buyPrice)>0 && (sellingPrice-buyPrice)>diffrence){
     *                 diffrence = sellingPrice-buyPrice;
     *             }
     *         }
     *
     *         return diffrence;
     *
     *     }
     * };
    * */



    /**
     * #### Solution : 2 #####
     * This solution uses max,min functions to do the comparison so it keeps the code clean.
     * And gets rid of the selling price variable by considering to sell on each iteration and comparing the profit in
     * each iteration.
     *
     * But this one performs exactly the same as my solution written above in terms of time and space complexity.
     *
     *      * Time Complixity --> O(n)
     *      * Space Complexity --> O(1)
    * */
    public int maxProfit(int[] prices) {
        int currentMin = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];
            currentMin = Math.min(price,currentMin);
            maxProfit = Math.max(maxProfit,price-currentMin);
        }
        return maxProfit;
    }

    public static void main(String[] args) {

        _1_StockBuyAndSell solution = new _1_StockBuyAndSell();

        int[] inputArr = new int[]{7,1,5,3,6,4};
        int result = solution.maxProfit(inputArr);
        System.out.println(result);

        int[] inputArr1 = new int[]{7,6,4,3,1};
        int result1 = solution.maxProfit(inputArr1);
        System.out.println(result1);

    }
}
