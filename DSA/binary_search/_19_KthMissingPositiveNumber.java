package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/kth-missing-positive-number/description/
 * Video Explanation : https://www.youtube.com/watch?v=BltzCxN1vRc
 *
 * @see _16_MinNumberOfDaysToMakeMBouquets similar problem.
 * @see _15_KokoEatingBananas similar problem.
 * @see _17_FindSmallestDivisorGivenAThreshold similar problem.
 *
 * 1539. Kth Missing Positive Number
 * Easy
 * Topics
 *
 * Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.
 * Return the kth positive integer that is missing from this array.
 *
 * Example 1:
 * Input: arr = [2,3,4,7,11], k = 5
 * Output: 9
 * Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
 *
 * Example 2:
 * Input: arr = [1,2,3,4], k = 2
 * Output: 6
 * Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 *
 * Constraints:
 *     1 <= arr.length <= 1000
 *     1 <= arr[i] <= 1000
 *     1 <= k <= 1000
 *     arr[i] < arr[j] for 1 <= i < j <= arr.length
 *
 * Follow up:
 * Could you solve this problem in less than O(n) complexity?
 *
 */
public class _19_KthMissingPositiveNumber {

    /**
     *
     * Time Complexity: O(n + k), where n is the length of the array
     * In the worst case, you iterate through all array elements (n) plus k missing numbers
     *
     * Space Complexity: O(1)
     * Only using a constant amount of extra space (three integer variables)
    * */
    public int findKthPositive(int[] arr, int k) {
        // arr = [2,3,4,7,11], k = 5
        int missingIndex=0;
        int index=0;
        int count=1;
        while(missingIndex<k){
            if (index<arr.length&&count==arr[index]){
                index++;
            }else {
                missingIndex++;
            }
            if (missingIndex<k) count++;
        }
        return count;
    }

    /**
    * Solution 2 :
     *
     * Time Complexity:
     * O(log n) — because the algorithm uses binary search over the sorted array arr.
     *
     * Space Complexity:
     * O(1) — only a few extra variables (start, end, mid, missingTillMid) are used, so it’s constant space.
    * */
    public int findKthPositive1(int[] arr, int k) {
        int start=0;
        int end= arr.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            int missingTillMid = arr[mid] - (mid+1);
            if (missingTillMid<k){
                start=mid+1;
            }else {
                end=mid-1;
            }
        }
        return start+k;
    }

    public static void main(String[] args) {

        _19_KthMissingPositiveNumber obj = new _19_KthMissingPositiveNumber();

        int[] piles = {2,3,4,7,11};
        int k = 5;
        System.out.println("Possible K : "+obj.findKthPositive(piles,k));
        System.out.println("Possible K : "+obj.findKthPositive1(piles,k));

        int[] piles1 = {1,2,3,4};
        int k1 = 2;
        System.out.println("Possible K : "+obj.findKthPositive(piles1,k1));
        System.out.println("Possible K : "+obj.findKthPositive1(piles1,k1));

    }
}