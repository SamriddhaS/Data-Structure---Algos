package DSA.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * Problem Link :
 * 1. https://takeuforward.org/data-structure/m-coloring-problem/
 * 2. https://leetcode.com/problems/flower-planting-with-no-adjacent/description/
 *
 * Video(intuition) : https://www.youtube.com/watch?v=052VkKhIaQ4
 *
 * Topic : TRecursion, TBacktracking, TDynamicProgramming
 * (Revisit) - Need Revision
 *
 * 1042. Flower Planting With No Adjacent
 *
 * You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional
 * path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.
 *
 * All gardens have at most 3 paths coming into or leaving it.
 * Your task is to choose a flower type for each garden such that, for any two gardens connected by a path,
 * they have different types of flowers.
 *
 * Return any such a choice as an array answer, where answer[i] is the type of flower
 * planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.
 *
 *
 * Example 1:
 * Input: n = 3, paths = [[1,2],[2,3],[3,1]]
 * Output: [1,2,3]
 * Explanation:
 * Gardens 1 and 2 have different types.
 * Gardens 2 and 3 have different types.
 * Gardens 3 and 1 have different types.
 * Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].
 *
 * Example 2:
 * Input: n = 4, paths = [[1,2],[3,4]]
 * Output: [1,2,1,2]
 *
 * Example 3:
 * Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 * 0 <= paths.length <= 2 * 104
 * paths[i].length == 2
 * 1 <= xi, yi <= n
 * xi != yi
 * Every garden has at most 3 paths coming into or leaving it.
 *
 *
 *
 */
public class _16_MColoringProblem {

    public boolean backtrackGarden(
            List<List<Integer>> connectedNeighbors,
            int[] firstAnswer,
            int currentGarden,
            int maxGarden,
            int noOfFlowers
    ) {
        // Base case
        if (currentGarden > maxGarden) {
            // add answer and return to end the recursive calls.
            return true;
        }

        for (int flower = 1; flower <= noOfFlowers; flower++) {
            // 1. First check if any neighbor has also assigned this current flower if yes we cant select it.
            // 2. If yes, skip that flower and check for next flower.
            // 3. After the neighbor checking, if flower is assignable ->
            // - add the color to this garden
            // - then call the backtrackGarden function again with next currentGarden

            List<Integer> neighbors = connectedNeighbors.get(currentGarden);
            boolean canAssign = true;
            for (Integer neighbor : neighbors) {
                if (flower == firstAnswer[neighbor - 1]) {
                    canAssign = false;
                }
            }
            if (canAssign) {
                // color is available continue to backtrack for next gardens.
                firstAnswer[currentGarden - 1] = flower;
                boolean res = backtrackGarden(connectedNeighbors, firstAnswer, currentGarden + 1, maxGarden, noOfFlowers);
                if (res) return true; // answer found break the recursive calls and return the answer
                firstAnswer[currentGarden - 1] = 0;
            }

        }

        return false;
    }

    /**
     * Algorithm Intuition :
     *
     * Problem: Assign flowers (1-4) to gardens such that no two connected gardens have the same flower type.
     * Solution Approach - Backtracking:
     *
     * - Graph Representation: Build adjacency list to quickly find neighbors of any garden
     * - Sequential Assignment: Try to assign flowers to gardens one by one (garden 1, then 2, then 3...)
     * - Constraint Checking: For each garden, try flowers 1-4, but skip any flower already used by a connected neighbor
     * - Backtracking: If we can't assign any valid flower to current garden, undo the previous assignment and try a different flower.
     * - Early Termination: Once we find the first valid complete assignment, return it immediately
     *
     * Key Insight: Since each garden has at most 3 neighbors and we have 4 flower types,
     * there's always at least 1 available flower for any garden.
     *
     * ## Complexity Analysis #########
     *
     * Time Complexity: O(m^n) in worst case
     * n = number of gardens.
     * m = number of flowers available.
     * Here it is : 4^n - we have 4 types of flower.
     *
     * In practice, much better due to constraints and early pruning
     *
     * Space Complexity: O(n + m) :
     * O(n) for adjacency list and answer array
     * O(m) for storing edges in adjacency list
     * O(n) for recursion stack depth
     *
     * Where n = number of gardens, m = number of paths
     *
    * */
    public int[] gardenNoAdj(int n, int[][] paths) {

        List<List<Integer>> connectedNeighbors = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            connectedNeighbors.add(new ArrayList<>());
        }

        for (int[] path : paths) {
            // Create the list of connected paths for easier checking of connected neighbors.
            int x = path[0];
            int y = path[1];
            connectedNeighbors.get(x).add(y);
            connectedNeighbors.get(y).add(x);
        }

        int[] answer = new int[n];
        backtrackGarden(connectedNeighbors, answer, 1, n, 4);
        return answer;
    }

    public static void main(String[] args) {

        _16_MColoringProblem solution = new _16_MColoringProblem();
        int[][] paths = {{1, 2}, {2, 3}, {3, 1}};
        int[] answer = solution.gardenNoAdj(3, paths);
        System.out.println("Answer : " + Arrays.toString(answer));

    }

}
