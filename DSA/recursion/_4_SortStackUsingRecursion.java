package recursion;

import java.util.Stack;

/**
 * Problem Link : https://www.geeksforgeeks.org/dsa/sort-a-stack-using-recursion/
 * Sort a stack using recursion
 */
public class _4_SortStackUsingRecursion {

    public static void sortedInsert(Stack<Integer> s, int x) {
        if (s.isEmpty()||x>s.peek()){
            s.push(x);
            return;
        }
        Integer temp = s.pop();
        sortedInsert(s,x);
        s.push(temp);
    }

    /**
     * Time Complexity: O(n^2).
     * Auxiliary Space: O(n) due to call stack and temporary stack.
    * */
    public static void sort(Stack<Integer> s) {
        if (s.isEmpty()){
            return;
        }
        Integer temp = s.pop();
        sort(s);
        sortedInsert(s,temp);
    }

    public static void main(String[] args) {

        _4_SortStackUsingRecursion solution = new _4_SortStackUsingRecursion();

        System.out.println("=== Stack Sorting Test Cases ===\n");

        // Test Case 1: Original test case - Mixed unsorted elements
        System.out.println("Test Case 1: Mixed unsorted elements");
        Stack<Integer> s1 = new Stack<>();
        s1.push(11);
        s1.push(2);
        s1.push(32);
        s1.push(3);
        s1.push(41);
        System.out.print("Before sorting: ");
        printStack(s1);
        sort(s1);
        System.out.print("After sorting:  ");
        printStack(s1);
        System.out.println();

        // Test Case 2: Empty stack
        System.out.println("Test Case 2: Empty stack");
        Stack<Integer> s2 = new Stack<>();
        System.out.print("Before sorting: ");
        printStack(s2);
        sort(s2);
        System.out.print("After sorting:  ");
        printStack(s2);
        System.out.println();

        // Test Case 3: Single element
        System.out.println("Test Case 3: Single element");
        Stack<Integer> s3 = new Stack<>();
        s3.push(42);
        System.out.print("Before sorting: ");
        printStack(s3);
        sort(s3);
        System.out.print("After sorting:  ");
        printStack(s3);
        System.out.println();

        // Test Case 4: Already sorted in ascending order (best case)
        System.out.println("Test Case 4: Already sorted (ascending)");
        Stack<Integer> s4 = new Stack<>();
        s4.push(1);
        s4.push(2);
        s4.push(3);
        s4.push(4);
        s4.push(5);
        System.out.print("Before sorting: ");
        printStack(s4);
        sort(s4);
        System.out.print("After sorting:  ");
        printStack(s4);
        System.out.println();

        // Test Case 5: Reverse sorted (worst case)
        System.out.println("Test Case 5: Reverse sorted (descending)");
        Stack<Integer> s5 = new Stack<>();
        s5.push(5);
        s5.push(4);
        s5.push(3);
        s5.push(2);
        s5.push(1);
        System.out.print("Before sorting: ");
        printStack(s5);
        sort(s5);
        System.out.print("After sorting:  ");
        printStack(s5);
        System.out.println();

        // Test Case 6: All elements are the same
        System.out.println("Test Case 6: All identical elements");
        Stack<Integer> s6 = new Stack<>();
        s6.push(7);
        s6.push(7);
        s6.push(7);
        s6.push(7);
        System.out.print("Before sorting: ");
        printStack(s6);
        sort(s6);
        System.out.print("After sorting:  ");
        printStack(s6);
        System.out.println();

        // Test Case 7: Two elements (minimum for comparison)
        System.out.println("Test Case 7: Two elements - unsorted");
        Stack<Integer> s7 = new Stack<>();
        s7.push(10);
        s7.push(5);
        System.out.print("Before sorting: ");
        printStack(s7);
        sort(s7);
        System.out.print("After sorting:  ");
        printStack(s7);
        System.out.println();

        // Test Case 8: Two elements - already sorted
        System.out.println("Test Case 8: Two elements - already sorted");
        Stack<Integer> s8 = new Stack<>();
        s8.push(5);
        s8.push(10);
        System.out.print("Before sorting: ");
        printStack(s8);
        sort(s8);
        System.out.print("After sorting:  ");
        printStack(s8);
        System.out.println();

        // Test Case 9: Negative numbers
        System.out.println("Test Case 9: Negative numbers");
        Stack<Integer> s9 = new Stack<>();
        s9.push(-5);
        s9.push(10);
        s9.push(-15);
        s9.push(0);
        s9.push(-1);
        System.out.print("Before sorting: ");
        printStack(s9);
        sort(s9);
        System.out.print("After sorting:  ");
        printStack(s9);
        System.out.println();

        // Test Case 10: Large numbers
        System.out.println("Test Case 10: Large numbers");
        Stack<Integer> s10 = new Stack<>();
        s10.push(Integer.MAX_VALUE);
        s10.push(1000000);
        s10.push(Integer.MIN_VALUE);
        s10.push(999999);
        System.out.print("Before sorting: ");
        printStack(s10);
        sort(s10);
        System.out.print("After sorting:  ");
        printStack(s10);
        System.out.println();

        // Test Case 11: Duplicates mixed with unique elements
        System.out.println("Test Case 11: Duplicates with unique elements");
        Stack<Integer> s11 = new Stack<>();
        s11.push(3);
        s11.push(1);
        s11.push(3);
        s11.push(2);
        s11.push(1);
        s11.push(2);
        s11.push(4);
        System.out.print("Before sorting: ");
        printStack(s11);
        sort(s11);
        System.out.print("After sorting:  ");
        printStack(s11);
        System.out.println();

        System.out.println("=== All test cases completed ===");
    }

    // Helper method to print stack contents without modifying the original stack
    public static void printStack(Stack<Integer> s) {
        if (s.isEmpty()) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        Stack<Integer> temp = new Stack<>();

        // Move elements to temp stack to reverse order for printing
        while (!s.isEmpty()) {
            temp.push(s.pop());
        }

        // Print elements (bottom to top of original stack)
        boolean first = true;
        while (!temp.isEmpty()) {
            if (!first) System.out.print(", ");
            System.out.print(temp.peek());
            s.push(temp.pop()); // Restore original stack
            first = false;
        }
        System.out.println("]");

    }


}
