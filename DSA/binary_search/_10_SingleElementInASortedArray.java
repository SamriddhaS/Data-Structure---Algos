package binary_search;

/**
 * Problem Link : https://leetcode.com/problems/single-element-in-a-sorted-array/
 * Video Explanation : https://www.youtube.com/watch?v=cw5XhYeAU5o
 * (The implementation I have made is a bit different to this video, but the general idea is similar
 * that before the single element pairs starts at even position and after the single element the pairs
 * start at odd positions.)
 *
 * 540. Single Element in a Sorted Array
 * Medium
 * Topics
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 * Return the single element that appears only once.
 * Your solution must run in O(log n) time and O(1) space.
 *
 * Example 1:
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Constraints:
 *     1 <= nums.length <= 105
 *     0 <= nums[i] <= 105
 *
 */
public class _10_SingleElementInASortedArray {

    /**
    * Solution 1 : This solution works but has time complexity of O(n)
    * */
    public int singleNonDuplicate(int[] nums) {
        if(nums.length==0) return -1;
        if(nums.length==1) return nums[0];
        int start=0,end=nums.length-1;
        int mid = (start+end)/2;
        while (start<mid){
            if(nums[start]!=nums[start+1]) return nums[start];
            start+=2;
        }
        while (end>=mid){
            if (nums[end]!=nums[end-1]) return nums[end];
            end-=2;
        }
        return -1;
    }

    /**
     * Solution 2 :
     * Time Complexity: O(log n)
     * Binary search - the search space is halved in each iteration.
     * Space Complexity: O(1)
     * Only uses a constant amount of extra variables (start, end, mid).
     *
     * Intuition :
     *
     * [1,1,3,3,5,5,7,9,9,11,11] - notice that all the pairs starts at even index like - 0,2,4
     * but this pattern breaks when we encounter the single element. See once we get the single
     * element 7 at index 6. After that all the pair start at odd indexes - 7,9.
     *
     * So we can use this observation to decide which half can contain the single element.
     * And based on that move our pointers to search inside that specific half.
     *
     * And when we are updating mid at the same time also check if we are pointing to the single element.
    * */
    public int singleNonDuplicate1(int[] nums) {
        if(nums.length==0) return -1;
        if(nums.length==1) return nums[0];
        int start=0,end=nums.length-1;
        while (start<=end){
            int mid = (start+end)/2;
            // boundary checking + check if this is the single element
            if (mid == 0 && nums[mid] != nums[mid+1]) return nums[mid];
            // boundary checking + check if this is the single element
            if (mid == nums.length-1 && nums[mid] != nums[mid-1]) return nums[mid];
            // found the element
            if (nums[mid-1]!=nums[mid]&&nums[mid+1]!=nums[mid]) return nums[mid];
            // didn't find the element so let's keep checking.
            if (mid%2==0){
                // - mid is at even position
                // - As per our observation if mid(even position) and our mid+1(next position) are not equal
                // that means pairs are not starting at even position rather its starting at odd position.
                // So that means the single element is somewhere to the left side (start - mid).
                // If mid(even position) and our mid+1(next position) are equal that means where the mid is
                // currently till that point all are pairs,so we should search to the right side of the array.
                if (nums[mid]!=nums[mid+1]) end=mid-1; // the single element must be in between start - mid
                else start = mid+1; // the single element must be between mid - end.
            }else {
                // - mid is at odd position
                // - As per our observation if mid(odd position) and our mid+1(next position) are not equal
                // that means pairs are still starting at even position.
                // So that means the single element is somewhere to the right side (mid - end).
                // If mid(odd position) and our mid+1(next position) are equal that means where the mid is
                // currently the single element is to the left side of the mid, that's why we are getting
                // paris at odd positions, so look for the single element to the left side.
                if (nums[mid]==nums[mid+1]) end=mid-1; // the single element must be in between start - mid
                else start = mid+1; // the single element must be between mid - end.
            }
        }
        return nums[end];
    }

    public static void main(String[] args) {

        _10_SingleElementInASortedArray obj = new _10_SingleElementInASortedArray();

        int[] input = {1,1,2,3,3,4,4,8,8};
        System.out.println("Out : "+ obj.singleNonDuplicate(input));
        System.out.println("Out : "+ obj.singleNonDuplicate1(input));

        int[] input1 = {3,3,7,7,10,11,11};
        System.out.println("Out : "+ obj.singleNonDuplicate(input1));
        System.out.println("Out : "+ obj.singleNonDuplicate1(input1));

        int[] input2 = {1,1,2,3,3};
        System.out.println("Out : "+ obj.singleNonDuplicate(input2));
        System.out.println("Out : "+ obj.singleNonDuplicate1(input2));

        int[] input3 = {1,1,2,3,3,4,4,8,8};
        System.out.println("Out : "+ obj.singleNonDuplicate(input3));
        System.out.println("Out : "+ obj.singleNonDuplicate1(input3));

        int[] input4 = {1,1,2};
        System.out.println("Out : "+ obj.singleNonDuplicate(input4));
        System.out.println("Out : "+ obj.singleNonDuplicate1(input4));
    }
}