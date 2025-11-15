package array_problems;

/**
 * Problem Link : https://leetcode.com/problems/powx-n/
 * Video : https://www.youtube.com/watch?v=7wcJXZoGKMI
 * Topic : TRecursion, TBacktracking, TArrays
 * 50. Pow(x, n)
 *
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 *
 * Example 1:
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 *
 * Example 2:
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 *
 * Example 3:
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 *
 * Constraints:
 *
 * -100.0 < x < 100.0
 * -231 <= n <= 231-1
 * n is an integer.
 * Either x is not zero or n > 0.
 * -104 <= xn <= 104
 *
 */
public class _30_NPowOfX {


    /**
     * Time Complexity: O(n)
     *
     * - The function calls itself n times for a positive exponent.
     * - Each recursive call performs a constant amount of work.
     * - This creates a linear chain of operations proportional to n.
     *
     * Space Complexity: O(n)
     *
     * - This is due to the call stack.
     * - Each recursive call adds a new stack frame.
     * - For an exponent n, there will be n stack frames at the deepest point of the recursion, requiring space proportional to n.
     *
    * */
    public double myPowRecursiveBrute(double x, int n) {
        if(n==0){
            return 1;
        }

        if(n<0){
            int posN = n * -1 ;
            return 1/myPowRecursiveBrute(x,posN);
        }

        return myPowRecursiveBrute(x,n-1) * x;
    }

    public double solve(double x,long n){
        // if n == even -> recursion(x*x,n/2)
        // if n == odd -> x * recursion(x*x,n/2)
        // if n <= 0 -> long x = -x;
        if (n==0) return 1;
        long longN = n ;
        if (n<0){
            longN = -n;
            return 1/solve(x,longN);
        }
        if (longN%2==0){
            return solve(x*x,longN/2);
        }else{
            longN = longN-1;
            return solve(x*x,longN/2) * x;
        }
    }


    /**
     * Time Complexity :
     * The time complexity is O(log n). This is because the recursive calls effectively halve the problem size (n) at each step.
     * The base case is reached when n becomes 0.
     * The number of steps it takes to reduce n to 0 by repeatedly dividing it by 2 is logarithmic.
     *
     * Space Complexity :
     * The space complexity is also O(log n). This is due to the recursion call stack. Each recursive call
     * adds a new stack frame to the memory. Since the depth of the recursion is logarithmic, the maximum number of stack
     * frames stored at any given time is proportional to logn. Therefore, the space required for the call stack is O(log n).
     *
     */
    public double myPowRecursiveOptimal(double x, int n) {
        return solve(x,n);
    }

    /**
     * Time Complexity :
     * The time complexity of this algorithm is O(log∣n∣).
     * This is because the core of the algorithm is a while loop where
     * the exponent pow is repeatedly divided by 2 (pow = pow / 2).
     * <p>
     * Space Complexity :
     * <p>
     * The space complexity of this algorithm is O(1).
     *
     */
    public double myPowIterative(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double res = 1.0;
        long pow = n;

        if (n < 0) {
            pow = -pow;
        }

        while (pow > 0) {
            if (pow % 2 == 1) {
                res = res * x;
            }
            pow = pow / 2;
            x = x * x;
        }

        if (n < 0) return (1 / res);
        else return res;
    }

    public static void main(String[] args) {

        _30_NPowOfX solution = new _30_NPowOfX();
        double x = 2.00000;
        int n = 114;

        long startTime = System.currentTimeMillis();
        System.out.println(solution.myPowRecursiveBrute(x, n));
        long endTime = System.currentTimeMillis();
        System.out.println("time taken 0 : " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(solution.myPowRecursiveOptimal(x, n));
        endTime = System.currentTimeMillis();
        System.out.println("time taken 1 : " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(solution.myPowIterative(x, n));
        endTime = System.currentTimeMillis();
        System.out.println("time taken 2 : " + (endTime - startTime));

    }


}
