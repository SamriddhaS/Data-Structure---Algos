package DSA.array_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MyClass {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        int size = nums.length-1;
        Set<List<Integer>> answer = new HashSet();
        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                for(int k=j+1;k<size;k++){
                    for(int l=k+1;l<size;l++){
                        if(nums[i]+nums[j]+nums[k]+nums[l]==target){
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                            Collections.sort(temp);
                            answer.add(temp);
                        }
                    }
                }
            }
        }
        return new ArrayList<>(answer);
    }

    public static void main(String[] args) {
        int[] nums = { 4, 3, 3, 4, 4, 2, 1, 2, 1, 1 };
        int target = 9;
        List<List<Integer>> ans = fourSum(nums, target);
        System.out.println("The quadruplets are: ");
        for (List<Integer> it : ans) {
            System.out.print("[");
            for (int ele : it) {
                System.out.print(ele + " ");
            }
            System.out.print("] ");
        }
        System.out.println();
    }
}
