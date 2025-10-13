package binary_search;

/**
 * Problem Link : https://takeuforward.org/binary-search/finding-sqrt-of-a-number-using-binary-search/
 * Video Explanation : https://www.youtube.com/watch?v=Bsv3FPUX_BA
 *
 * Problem Statement: You are given a positive integer n. Your task is to find and return its square root.
 * If ‘n’ is not a perfect square, then return the floor value of 'sqrt(n)'.
 *
 * Note: The question explicitly states that if the given number, n, is not a perfect square,
 * our objective is to find the maximum number, x, such that x squared is less than or
 * equal to n (x*x <= n). In other words, we need to determine the floor value of the square root of n.
 *
 * Example 1:
 * Input Format: n = 36
 * Result: 6
 * Explanation: 6 is the square root of 36.
 *
 * Example 2:
 * Input Format: n = 28
 * Result: 5
 * Explanation: Square root of 28 is approximately 5.292. So, the floor value will be 5.
 */
public class _13_FindingSqrtOfANumberUsingBinarySearch {


    /**
     * Time: O(log n) - binary search to find square root.
     * Space: O(1)
    * */
    public int findSqrt(int num) {
        int start=1,end=num,ans = 1;
        while(start<=end){
            int mid = (start+end)/2;
            if (mid*mid>num){
                end = mid-1;
            }else if (mid*mid<num){
                ans = mid;
                start = mid+1;
            }else {
                return mid;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        _13_FindingSqrtOfANumberUsingBinarySearch obj = new _13_FindingSqrtOfANumberUsingBinarySearch();

        System.out.println("Out : "+ obj.findSqrt(9));
        System.out.println("Out : "+ obj.findSqrt(10));
        System.out.println("Out : "+ obj.findSqrt(16));
        System.out.println("Out : "+ obj.findSqrt(24));
        System.out.println("Out : "+ obj.findSqrt(28));
        System.out.println("Out : "+ obj.findSqrt(36));
        System.out.println("Out : "+ obj.findSqrt(42));
        System.out.println("Out : "+ obj.findSqrt(48));
        System.out.println("Out : "+ obj.findSqrt(56));
        System.out.println("Out : "+ obj.findSqrt(62));
        System.out.println("Out : "+ obj.findSqrt(69));
        System.out.println("Out : "+ obj.findSqrt(72));
        System.out.println("Out : "+ obj.findSqrt(81));
        System.out.println("Out : "+ obj.findSqrt(92));
        System.out.println("Out : "+ obj.findSqrt(128));
    }
}