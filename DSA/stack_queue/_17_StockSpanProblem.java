package stack_queue;


import java.util.*;

/**
 * Problem Link : https://leetcode.com/problems/online-stock-span/description/
 * Video Explanation : https://www.youtube.com/watch?v=eay-zoSRkVc
 *
 * 901. Online Stock Span
 * Solved
 * Medium
 *
 * Design an algorithm that collects daily price quotes for some stock and
 * returns the span of that stock's price for the current day.
 *
 * The span of the stock's price in one day is the maximum number of
 * consecutive days (starting from that day and going backward) for which
 * the stock price was less than or equal to the price of that day.
 *
 * For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of
 * the stock today is 2, then the span of today is 4 because starting from today, the price of
 * the stock was less than or equal 2 for 4 consecutive days.
 *
 * Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of
 * the stock today is 8, then the span of today is 3 because starting from today,
 * the price of the stock was less than or equal 8 for 3 consecutive days.
 *
 * Implement the StockSpanner class:
 *     StockSpanner() Initializes the object of the class.
 *     int next(int price) Returns the span of the stock's price given that today's price is price.
 *
 * Example 1:
 * Input
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 * Output
 * [null, 1, 1, 1, 2, 1, 4, 6]
 *
 * Explanation
 * StockSpanner stockSpanner = new StockSpanner();
 * stockSpanner.next(100); // return 1
 * stockSpanner.next(80);  // return 1
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(70);  // return 2
 * stockSpanner.next(60);  // return 1
 * stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
 * stockSpanner.next(85);  // return 6
 *
 *
 *
 * Constraints:
 *     1 <= price <= 105
 *     At most 104 calls will be made to next.
 */
public class _17_StockSpanProblem {

    class Pair{
        int price,consecutiveDays;
        Pair(int price,int consecutiveDays){
            this.price = price;
            this.consecutiveDays = consecutiveDays;
        }
    }

    Stack<Pair> stack;

    /**
     * Overall Complexity for n calls
     *
     * Time Complexity: O(n)
     *
     * Across all n next() calls, each price is:
     * - Pushed onto stack exactly once: O(n) total
     * - Popped from stack at most once: O(n) total
     * - Total operations = O(n) + O(n) = O(n)
     *
     * Space Complexity: O(n)
     * - Stack size depends on the price sequence
     * - Worst case (monotonically increasing): All n elements remain on stack
     *
     * Summary: For n next() calls overall, it's O(n) time and O(n) space.
     *
    * */
    public _17_StockSpanProblem() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int days=1; // For counting today we add 1.
        while(!stack.isEmpty() && price>=stack.peek().price){
            days+=stack.pop().consecutiveDays;
        }
        stack.push(new Pair(price,days));
        return days;
    }

    public static void main(String[] args) {

        _17_StockSpanProblem stockSpanner = new _17_StockSpanProblem();
        System.out.println("Ans : "+stockSpanner.next(100)); // return 1
        System.out.println("Ans : "+stockSpanner.next(80)); // return 1
        System.out.println("Ans : "+stockSpanner.next(60)); // return 1
        System.out.println("Ans : "+stockSpanner.next(70)); // return 2
        System.out.println("Ans : "+stockSpanner.next(60)); // return 1
        System.out.println("Ans : "+stockSpanner.next(75)); // return 4
        // prices (including today's price of 75) were less than or equal to today's price.
        System.out.println("Ans : "+stockSpanner.next(85)); // return 6

        System.out.println("================================================================");
        //["StockSpanner","next","next","next","next","next","next","next","next","next","next"]
        //[[],[28],[14],[28],[35],[46],[53],[66],[80],[87],[88]]
        //expected - [null,1,1,3,4,5,6,7,8,9,10]
        _17_StockSpanProblem stockSpanner2 = new _17_StockSpanProblem();
        System.out.println("Ans : "+stockSpanner2.next(28)); // return 1
        System.out.println("Ans : "+stockSpanner2.next(14)); // return 1
        System.out.println("Ans : "+stockSpanner2.next(28)); // return 3
        System.out.println("Ans : "+stockSpanner2.next(35)); // return 4
        System.out.println("Ans : "+stockSpanner2.next(46)); // return 5
        System.out.println("Ans : "+stockSpanner2.next(53)); // return 6
        System.out.println("Ans : "+stockSpanner2.next(66)); // return 7
        System.out.println("Ans : "+stockSpanner2.next(80)); // return 8
        System.out.println("Ans : "+stockSpanner2.next(87)); // return 9
        System.out.println("Ans : "+stockSpanner2.next(88)); // return 10

    }
}