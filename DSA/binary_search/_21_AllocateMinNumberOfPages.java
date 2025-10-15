package binary_search;

import java.util.Arrays;

/**
 * Problem Link : https://takeuforward.org/data-structure/allocate-minimum-number-of-pages/
 * https://www.naukri.com/code360/problems/allocate-books_1090540?leftPanelTabValue=PROBLEM
 *
 * Video Explanation : https://www.youtube.com/watch?v=Z0hwjftStI4
 *
 *
 * Allocate Minimum Number of Pages
 *
 * Problem Statement: Given an array arr of integer numbers, ‘ar[i]’ represents the number of
 * pages in the ‘i-th’ book. There are a ‘m’ number of students, and the task is to allocate all the
 * books to the students.
 *
 * Allocate books in such a way that:
 *     Each student gets at least one book.
 *     Each book should be allocated to only one student.
 *     Book allocation should be in a contiguous manner.
 *
 * You have to allocate the book to ‘m’ students such that the maximum number of pages assigned to a
 * student is minimum. If the allocation of books is not possible. return -1
 *
 * Examples
 *
 * Example 1:
 * Input Format: n = 4, m = 2, arr[] = {12, 34, 67, 90}
 * Result: 113
 * Explanation: The allocation of books will be 12, 34, 67 | 90. One student will get the first 3 books and the other will get the last one.
 *
 * Example 2:
 * Input Format: n = 5, m = 4, arr[] = {25, 46, 28, 49, 24}
 * Result: 71
 * Explanation: The allocation of books will be 25, 46 | 28 | 49 | 24.
 *
 * We can allocate books in several ways but it is clearly said in the question that we have to allocate
 * the books in such a way that the maximum number of pages received by a student should be minimum.
 *
 * Assume the given array is {25 46 28 49 24} and number of students, M = 4. Now,
 * we can allocate these books in different ways. Some of them are the following:
 *
 *     25 | 46 | 28 | 49, 24  → Maximum no. of pages a student receive = 73
 *     25 | 46 | 28, 49 | 24  → Maximum no. of pages a student receive = 77
 *     25 | 46, 28 | 49 | 24  → Maximum no. of pages a student receive = 74
 *     25, 46 | 28 | 49 | 24  → Maximum no. of pages a student receive = 71
 *
 * From the above allocations, we can clearly observe that the minimum possible maximum number of pages is 71.
 *
 */
public class _21_AllocateMinNumberOfPages {

    private int canAllocateStudents(int[] pages,int maxAllowedPages){
        int student=1;
        long currentPages=0;
        for (int i = 0; i < pages.length; i++) {
            if (currentPages+pages[i]<=maxAllowedPages){
                currentPages+=pages[i];
            }else {
                student++;
                currentPages = pages[i];
            }
        }
        return student;
    }

    /**
     *
     * Time Complexity: O(n × (sum - max))
     * Outer loop: Iterates from minPages to maxPages → O(sum - max) iterations
     * Inner function canAllocateStudents: O(n) per call
     * Total: O(n × (sum - max)) where sum is total pages and max is largest book
     *
     * Space Complexity: O(1)
     * Only uses constant extra space for variables
    * */
    public int findMinNumberOfPages(int[] pages,int students){
        if (pages.length<students) return -1;
        int minPages = pages[0];
        int maxPages = 0;
        for (int i = 0; i < pages.length; i++) {
            minPages = Math.max(minPages,pages[i]);
            maxPages+=pages[i];
        }
        for (int i = minPages; i < maxPages; i++) {
            if (canAllocateStudents(pages, i)==students) return i;
        }
        return -1;
    }

    /**
     *
     * Time Complexity: O(n × log(sum - max))
     * Binary search: O(log(sum - max)) iterations
     * Each iteration calls canAllocateStudents: O(n)
     * Total: O(n × log(sum - max))
     *
     * Space Complexity: O(1)
     * Only uses constant extra space for variables
     *
    * */
    public int findMinNumberOfPages1(int[] pages,int students){
        if (pages.length<students) return -1;
        int minPages = pages[0];
        int maxPages = 0;
        for (int i = 0; i < pages.length; i++) {
            minPages = Math.max(minPages,pages[i]);
            maxPages+=pages[i];
        }

        while(minPages<=maxPages){
            int maxAllowedPage = (minPages+maxPages)/2;
            int noOfStudents = canAllocateStudents(pages,maxAllowedPage);
            if (noOfStudents<=students){
                maxPages = maxAllowedPage-1;
            }else {
                minPages = maxAllowedPage+1;
            }
        }

        return minPages;
    }


    public static void main(String[] args) {

        _21_AllocateMinNumberOfPages obj = new _21_AllocateMinNumberOfPages();

        // Test Case 1: Original example
        int[] stalls1 = {12, 34, 67, 90};
        int students = 2;
        System.out.println("Test 1 - Expected: 113, Got: " + obj.findMinNumberOfPages(stalls1, students));
        System.out.println("Test 1 - Expected: 113, Got: " + obj.findMinNumberOfPages1(stalls1, students));

        // Test Case 2: Original example
        int[] stalls2 = {25, 46, 28, 49, 24};
        int students2 = 4;
        System.out.println("Test 2 - Expected: 71, Got: " + obj.findMinNumberOfPages(stalls2, students2));
        System.out.println("Test 2 - Expected: 71, Got: " + obj.findMinNumberOfPages1(stalls2, students2));

        // Test Case 2: Original example
        int[] stalls3 = {2, 8, 8, 4, 5};
        int students3 = 6;
        System.out.println("Test 3 - Expected: -1, Got: " + obj.findMinNumberOfPages(stalls3, students3));
        System.out.println("Test 3 - Expected: -1, Got: " + obj.findMinNumberOfPages1(stalls3, students3));

    }
}