package DSA.recursion;

import java.sql.Time;

/**
 * Problem Link : https://leetcode.com/problems/count-good-numbers/description/
 * Explanation : https://www.youtube.com/watch?v=y_XVeVUpdP4&ab_channel=NeetCodeIO
 *
* */
public class _2_CountGoodNumbers {

    private static final long MOD = (long) (Math.pow(10,9)+7);

    public long toPowOfRecursive(long number,int n){

        // n^0 = 1
        if (n == 0) {
            return 1;
        }

        // Base case: any number to the power of 1 is itself
        if (n == 1) {
            return number % MOD;
        }

        return (number * toPowOfRecursive(number, n - 1))%MOD;
    }

    /**
     * Brute Force Solution Can be something like this one.
     *
     * #########################################################
     * Time Complexity: O(n)
     * Analysis:
     *
     * countGoodNumbers calls toPowOf twice.
     * First call: toPowOf(5, spaceForEvenNumbers) where spaceForEvenNumbers ≈ n/2
     * Second call: toPowOf(4, spaceForPrime) where spaceForPrime ≈ n/2
     * Each toPowOf(base, exp) makes exp recursive calls
     * Total recursive calls ≈ n/2 + n/2 = n
     * Each recursive call does O(1) work (one multiplication and one modulo)
     *
     * Therefore: O(n/2 + n/2) = O(n)
     *
     * #########################################################
     * Space Complexity: O(n)
     * Analysis:
     *
     * Your recursive toPowOf function creates a call stack
     * Maximum recursion depth ≈ n/2 (for the larger of the two calls)
     * Each recursive call adds one frame to the call stack
     * Each stack frame stores constant amount of data (parameters, return address)
     *
     * Therefore: O(n) space due to recursion stack
     *
     *#########################################################
     * This solution will not work if n = large number.
     * Example :
     * For n = 806166225460393 :
     *
     * spaceForEvenNumbers will be around 403083112730197
     * Recursive function will try to make ~400 trillion recursive calls!
     * Each call adds a frame to the call stack
     * The stack has limited memory (typically a few MB)
     * Result: Stack Overflow
     *
    * */
    public int countGoodNumbers(long n) {
        int spaceForEvenNumbers = (int) Math.ceil((double) n/2);
        int spaceForPrime = (int) Math.floor((double) n/2);
        long result = (toPowOfRecursive(5,spaceForEvenNumbers) * toPowOfRecursive(4,spaceForPrime))%MOD;
        int res = (int) result;
        System.out.println("Result : "+res);
        return res;
    }


    public long toPowOfIterative(long number, long n) {
        if (n == 0) return 1;

        long result = 1;
        number = number % MOD;  // Handle case where number >= MOD

        while (n > 0) {
            result = (result * number) % MOD;
            n--;
        }

        return result;
    }

    /**
     * Same time & space complexity as the 1st solution.
     *
     * This is the same solution but for calculating the power of we are using iteration.
     * So this will not cause StackOverflow error like first one but it will give you TLE in leetcode.
     * As this iterative way will take much time when n value is very large.
    * */
    public int countGoodNumbersIterative(long n) {
        int spaceForEvenNumbers = (int) Math.ceil((double) n/2);
        int spaceForPrime = (int) Math.floor((double) n/2);
        long result = (toPowOfIterative(5,spaceForEvenNumbers) * toPowOfIterative(4,spaceForPrime))%MOD;
        int res = (int) result;
        System.out.println("Result : "+res);
        return res;
    }


    public long toPowOfFast(long number, long n) {
        if (n == 0) return 1;
        long result = 1;
        while (n > 0){
            if (n%2==1){
                result = (result*number)%MOD;
            }
            n=n/2;
            number = (number*number)%MOD;
        }
        return result;
    }

    /**
     * Time Complexity: O(log n)
     *
     * countGoodNumbersOptimal() calls toPowOfFast() twice
     * Each toPowOfFast() call runs in O(log n) time
     * The while loop in toPowOfFast() runs log₂(exponent) times because n is halved each iteration
     * input n = 806166225460393, it only takes ~50 iterations!
     *
     * Space Complexity: O(1)
     *
     * Uses only constant extra space
     * No recursion (no call stack growth)
     * Only stores a few variables: result, number, n.
    * */
    public int countGoodNumbersOptimal(long n) {
        long spaceForEvenNumbers = (long) Math.ceil((double) n/2);
        long spaceForPrime = (long) Math.floor((double) n/2);
        long result = (toPowOfFast(5,spaceForEvenNumbers) * toPowOfFast(4,spaceForPrime))%MOD;
        int res = (int) result;
        System.out.println("Result : "+res);
        return res;
    }

    public static void main(String[] args) {
        _2_CountGoodNumbers solution = new _2_CountGoodNumbers();
        long n = 1000000000;
        long startTime = System.currentTimeMillis();
        solution.countGoodNumbersIterative(n);
        long endTime = System.currentTimeMillis();
        System.out.println("time taken 1 : "+(endTime-startTime));

        startTime = System.currentTimeMillis();
        solution.countGoodNumbersOptimal(n);
        endTime = System.currentTimeMillis();
        System.out.println("time taken 2 : "+(endTime-startTime));
    }


}
