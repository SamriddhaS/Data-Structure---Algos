package DSA.recursion;

/**
 * Problem Link : https://leetcode.com/problems/count-good-numbers/description/
 * Explanation : https://www.youtube.com/watch?v=y_XVeVUpdP4&ab_channel=NeetCodeIO
 *
* */
public class _2_CountGoodNumbers {

    public long toPowOf(long number,int n){
        if (n == 0) {
            return 1;
        }
        // Base case: any number to the power of 1 is itself
        if (n == 1) {
            return number;
        }
        return number * toPowOf(number, n - 1);
    }

    int MOD = 1000000007;

    public int countGoodNumbers(long n) {
        int spaceForEvenNumbers = (int) Math.ceil((double) n/2);
        int spaceForPrime = (int) Math.floor((double) n/2);
        long result = toPowOf(5,spaceForEvenNumbers) * toPowOf(4,spaceForPrime);
        int res = (int) result%MOD;
        System.out.println("Result : "+res);
        return res;
    }

    public static void main(String[] args) {
        _2_CountGoodNumbers solution = new _2_CountGoodNumbers();
        solution.countGoodNumbers(4);
    }


}
