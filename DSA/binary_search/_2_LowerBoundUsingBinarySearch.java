package binary_search;

/**
 * Problem Link : https://takeuforward.org/arrays/implement-lower-bound-bs-2/
 * Video Explanation : https://www.youtube.com/watch?v=6zhGS79oQ4k
 *
 *
 */
public class _2_LowerBoundUsingBinarySearch {


    /**
     *
     * Time Complexity: O(log N)
     * Binary search divides the search space in half with each iteration
     * Maximum iterations = log₂(N)
     *
     * Space Complexity: O(1)
     * Only uses a constant amount of extra variables (start, end, mid, ans)
     * No recursion or additional data structures
     *
     * Key idea:
     * - If nums[mid] >= target: This could be our answer, but there might be a smaller index to the left,
     * so search left (end = mid-1) and update ans = mid
     * - If nums[mid] < target: The answer must be to the right,
     * - so search right (start = mid+1)
     * - Initialize ans = nums.length to handle the case where no element is ≥ target
     *
     * By always moving towards smaller valid indices when a candidate is found,
     * we ensure we get the first position where the condition holds.
     *
    * */
    public int findLowerBound(int[] nums, int target) {
        int start=0,end = nums.length-1;
        int ans=nums.length;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]>target){
                end = mid-1;
                if (nums[mid]>=target) ans = mid;
            }else if(nums[mid]<target){
                start = mid+1;
            }else{
                ans = mid;
                end = mid-1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        _2_LowerBoundUsingBinarySearch obj = new _2_LowerBoundUsingBinarySearch();

        int[] input = {3,5,8,15,19};
        int target = 9;
        System.out.println("Out : "+obj.findLowerBound(input,target));

        int[] input1 = {1, 2, 2, 2, 5, 7};
        int target1 = 2;
        System.out.println("Out : "+obj.findLowerBound(input1,target1));

        int[] input2 = {1,3,5,7,9};
        int target2 = 6;
        System.out.println("Out : "+obj.findLowerBound(input2,target2));
    }
}