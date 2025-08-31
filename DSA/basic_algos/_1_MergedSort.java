package DSA.basic_algos;


/**
 * Video : https://www.youtube.com/watch?v=ogjf7ORKfd8
 *
 *
* */
class _1_MergedSort {

    private void merge(int[] inputArray,int start,int end,int mid){
        int leftPointer=start;
        int rightPointer=mid+1;
        int[] sortedArr = new int[end-start+1];
        int index=0;
        while (leftPointer<=mid&&rightPointer<=end){
            if (inputArray[leftPointer]<inputArray[rightPointer]){
                sortedArr[index] = inputArray[leftPointer];
                leftPointer++;
            }else{
                sortedArr[index] = inputArray[rightPointer];
                rightPointer++;
            }
            index++;
        }

        // if remaining items are there copy them to answer
        while (leftPointer<=mid){
            sortedArr[index] = inputArray[leftPointer];
            leftPointer++;
            index++;
        }

        // if remaining items are there copy them to answer
        while (rightPointer<=end){
            sortedArr[index] = inputArray[rightPointer];
            rightPointer++;
            index++;
        }

        // copy the sorted array into our main array.
        index=0;
        while (start<=end){
            inputArray[start] = sortedArr[index];
            start++;
            index++;
        }
    }

    private void divideAndMerge(int[] inputArray,int start,int end){
        // 1 : base case
        if (start==end) return;

        // 2 : now lets divide the array into smaller parts
        int mid = (start+end)/2;
        divideAndMerge(inputArray,start,mid); // 1st half
        divideAndMerge(inputArray,mid+1,end); // 2nd half

        //3 : last step is to merge the array's.
        merge(inputArray,start,end,mid);
    }

    /**
    * Time Complexity Analysis:
     *
     * - Divide Phase: O(log n)
     *      Array is split in half at each level
     *      Creates log₂(n) levels of recursion
     * - Merge Phase: O(n) per level
     *      Each level processes all n elements exactly once
     *      Comparing and copying elements is O(1) per element
     *
     * - Overall: O(n log n)
     *
     * O(n) work × O(log n) levels = O(n log n)
     * This holds for best, average, and worst cases
     *
     *
     *
     * Space Complexity Analysis:
     *
     * - Temporary Arrays: O(n)
     *      Each merge creates a temporary array of size (end-start+1)
     *      At any given time, total temporary space ≤ O(n)
     * - Recursion Stack: O(log n)
     *      Maximum recursion depth is log₂(n)
     *      Each recursive call uses O(1) additional space
     *
     * - Overall: O(n)
     *      Temporary arrays dominate: O(n) + O(log n) = O(n)
     *
    * */
    public int[] mergeSort(int[] inputArray){
        divideAndMerge(inputArray,0,inputArray.length-1);
        return inputArray;
    }

    public static void main(String[] args) {

        _1_MergedSort solution = new _1_MergedSort();

        int[] result = solution.mergeSort(new int[]{11,-11,0,-2,999,-7,123221,222,0,1,11,-11,2,32,4,50});
        System.out.print("{");
        for (int num : result){
            System.out.print(" "+num);
        }
        System.out.print("}");

    }
}
