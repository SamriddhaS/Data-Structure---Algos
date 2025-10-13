package binary_search;

/**
 * Problem Link : https://takeuforward.org/data-structure/nth-root-of-a-number-using-binary-search/
 * Video Explanation : https://www.youtube.com/watch?v=rjEJeYCasHs
 *
 * Nth Root of a Number using Binary Search
 * Problem Statement: Given two numbers N and M, find the Nth root of M.
 * The nth root of a number M is defined as a number X when raised to the power N equals M.
 * If the nth root is not an integer, return -1.
 *
 * Example 1:
 * Input Format: N = 3, M = 27
 * Result: 3
 * Explanation: The cube root of 27 is equal to 3.
 *
 * Example 2:
 * Input Format: N = 4, M = 69
 * Result: -1
 * Explanation: The 4th root of 69 does not exist. So, the answer is -1.
 */
public class _14_NthRootOfANumberUsingBinarySearch {


    /**
     * - Time Complexity: O(log M × log N)
     * Breakdown:
     * Binary Search: O(log M)
     * You're searching from 2 to M, so the search space is M
     * Binary search divides the space in half each time
     *
     * Math.pow() inside loop: O(log N)
     * Math.pow(mid, N) takes O(log N) time for exponentiation
     * This operation happens in every iteration of binary search
     *
     * Total: O(log M × log N)
     *
     * - Space Complexity: O(1)
    * */
    public int findSqrt(int N,int M) {
        int start=2,end=M;
        while (start<=end){
            int mid = (start+end)/2;
            long powerFactor = (long) Math.pow(mid, N);
            if (powerFactor>M){
                end = mid-1;
            } else if (powerFactor<M) {
                start = mid+1;
            }else {
               return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        _14_NthRootOfANumberUsingBinarySearch obj = new _14_NthRootOfANumberUsingBinarySearch();

        // Basic cases
        System.out.println("Out: " + obj.findSqrt(3, 27) + " | Expected: 3");
        System.out.println("Out: " + obj.findSqrt(4, 69) + " | Expected: -1");

        // Perfect roots
        System.out.println("Out: " + obj.findSqrt(2, 16) + " | Expected: 4");
        System.out.println("Out: " + obj.findSqrt(2, 64) + " | Expected: 8");
        System.out.println("Out: " + obj.findSqrt(5, 32) + " | Expected: 2");
        System.out.println("Out: " + obj.findSqrt(3, 125) + " | Expected: 5");
        System.out.println("Out: " + obj.findSqrt(4, 256) + " | Expected: 4");
        System.out.println("Out: " + obj.findSqrt(6, 64) + " | Expected: 2");

        // Non-perfect roots (should return -1)
        System.out.println("Out: " + obj.findSqrt(2, 10) + " | Expected: -1");
        System.out.println("Out: " + obj.findSqrt(3, 100) + " | Expected: -1");
        System.out.println("Out: " + obj.findSqrt(4, 100) + " | Expected: -1");

        // Edge cases
        System.out.println("Out: " + obj.findSqrt(1, 5) + " | Expected: 5");  // Any number to power 1
        System.out.println("Out: " + obj.findSqrt(1, 100) + " | Expected: 100");
        System.out.println("Out: " + obj.findSqrt(2, 1) + " | Expected: 1");  // 1^N = 1 for any N
        System.out.println("Out: " + obj.findSqrt(5, 1) + " | Expected: 1");
        System.out.println("Out: " + obj.findSqrt(3, 8) + " | Expected: 2");  // 2^3 = 8

        // Larger numbers
        System.out.println("Out: " + obj.findSqrt(2, 1024) + " | Expected: 32");
        System.out.println("Out: " + obj.findSqrt(3, 1000) + " | Expected: 10");
        System.out.println("Out: " + obj.findSqrt(2, 10000) + " | Expected: 100");

        // Zero case
        System.out.println("Out: " + obj.findSqrt(2, 0) + " | Expected: 0");  // 0^N = 0
        System.out.println("Out: " + obj.findSqrt(5, 0) + " | Expected: 0");

    }
}