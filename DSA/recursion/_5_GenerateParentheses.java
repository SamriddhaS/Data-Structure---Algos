package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Link : https://leetcode.com/problems/generate-parentheses/
 * Video : https://www.youtube.com/watch?v=s9fokUqJ76A&t=608s
 * https://www.youtube.com/watch?v=7xkPbffc6w8
 * Topic : TRecursion, TBacktracking
 *
 * (Revisit) Linked Topic : DP - Catalan Numbers : https://www.youtube.com/watch?v=eUw9A1wsFg8
 *
 * 22. Generate Parentheses
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * Example 1:
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 *
 * Example 2:
 * Input: n = 1
 * Output: ["()"]
 *
 * Constraints :
 *     1 <= n <= 8
 */
public class _5_GenerateParentheses {

    /**
     * Brute Force Approach
     *
     * Time Complexity: O(2^(2n) × n)
     *
     * Generates all possible combinations of '(' and ')' characters: 2^(2n) combinations
     * For each combination, validates it using isValid() method: O(n) time
     * Total: O(2^(2n) × n)
     *
     * Space Complexity: O(2^(2n) × n)
     *
     * Recursion depth: O(2n) for generating combinations
     * Stores all 2^(2n) combinations (each of length 2n) before filtering
     * Result list contains only valid combinations: O(C(n) × n) where C(n) is the nth Catalan number
     * Overall dominated by temporary storage: O(2^(2n) × n)
    * */
    public List<String> generateParenthesisBruteForce(int n) {
        List<String> result = new ArrayList<>();
        generateAllCombinations("", 2 * n, result, n);
        return result;
    }

    private void generateAllCombinations(String current, int remaining, List<String> result, int n) {
        if (remaining == 0) {
            if (isValid(current)) {
                result.add(current);
            }
            return;
        }

        generateAllCombinations(current + "(", remaining - 1, result, n);
        generateAllCombinations(current + ")", remaining - 1, result, n);
    }

    private boolean isValid(String s) {
        int balance = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false; // More closing than opening
        }
        return balance == 0; // Equal opening and closing
    }


    public void backtrackParenthesis(int openCount,int closeCount,int n,List<String> answerList,StringBuilder current){
        // base case
        if (n==openCount&&n==closeCount){
            answerList.add(current.toString());
            return;
        }
        if(openCount<n){
            current.append("(");
            backtrackParenthesis(openCount+1,closeCount,n,answerList,current);
            current.deleteCharAt(current.length()-1);
        }

        if(closeCount<openCount){
            current.append(")");
            backtrackParenthesis(openCount,closeCount+1,n,answerList,current);
            current.deleteCharAt(current.length()-1);
        }

    }

    /**
     * Backtracking Approach
     * Time Complexity: O(C(n) × n)
     *
     * Only generates valid combinations using pruning conditions
     * Number of valid combinations = nth Catalan number: C(n) = (2n)!/(n!(n+1)!) ≈ 4^n/(n√πn)
     * Each valid combination requires O(n) time to build the string
     * Total: O(C(n) × n)
     *
     * Space Complexity: O(n)
     *
     * Recursion depth: O(2n) = O(n) maximum
     * StringBuilder current: O(n) space
     * Result list: O(C(n) × n) but this is output space, not auxiliary
     * Auxiliary space complexity: O(n)
    * */
    public List<String> generateParenthesis(int n) {
        List<String> answerList = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        backtrackParenthesis(0,0,n,answerList,current);
        return answerList;
    }

    /**
    * Revisited on : 16th November 2025
    * */
    /*public void solve(ArrayList<String> answer,StringBuilder currentPath,int openCount,int closeCount,int n){
        // base case
        if(openCount==n&&closeCount==n){
            answer.add(currentPath.toString());
            return;
        }

        if(openCount<n){
            currentPath.append("(");
            solve(answer,currentPath,openCount+1,closeCount,n);
            currentPath.deleteCharAt(currentPath.length()-1);
        }

        if(closeCount<openCount){
            currentPath.append(")");
            solve(answer,currentPath,openCount,closeCount+1,n);
            currentPath.deleteCharAt(currentPath.length()-1);
        }

    }

    public List<String> generateParenthesis(int n) {
        // at every step i have 2 decisions -> add a '(' or add ')'
        ArrayList<String> answer = new ArrayList<>();
        StringBuilder currentPath = new StringBuilder();
        solve(answer,currentPath,0,0,n);
        return answer;
    }*/

    public static void main(String[] args) {

        _5_GenerateParentheses solution = new _5_GenerateParentheses();
        System.out.println("Possible Solutions : "+solution.generateParenthesis(3));
    }

}
