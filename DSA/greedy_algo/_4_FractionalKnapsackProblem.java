package DSA.greedy_algo;

import java.util.Arrays;

/**
 * Problem Link : https://takeuforward.org/data-structure/fractional-knapsack-problem-greedy-approach/
 * Concept Video : https://www.youtube.com/watch?v=oTTzNMHM05I&t=45s
 *
 * Fractional Knapsack Problem : Greedy Approach
 *
 * Problem Statement: The weight of N items and their corresponding values are given. We have to put these
 * items in a knapsack of weight W such that the total value obtained is maximized.
 *
 * Note: We can either take the item as a whole or break it into smaller units.
 *
 * Example:
 *
 * Input: N = 3, W = 50, values[] = {100,60,120}, weight[] = {20,10,30}.
 *
 * Output: 240.00
 *
 * Explanation: The first and second items  are taken as a whole  while only 20 units of the third item is taken.
 * Total value = 100 + 60 + 80 = 240.00
 *
 */
public class _4_FractionalKnapsackProblem {

    static  class Item implements Comparable<Item> {
        int value, weight;
        float valuePerWeight;
        Item(int x, int y) {
            this.value = x;
            this.weight = y;
        }

        public void setValuePerWeight(float valuePerWeight) {
            this.valuePerWeight = valuePerWeight;
        }

        @Override
        public int compareTo(Item o) {
            return Float.compare(o.valuePerWeight, this.valuePerWeight);
        }
    }

    /**
     * Time Complexity: O(n log n)
     *
     * The algorithm has three main phases:
     *
     * Calculate value-to-weight ratios: O(n)
     * Sort items by value-to-weight ratio: O(n log n)
     * Select items greedily: O(n)
     *
     * Space Complexity: O(1)
     * The algorithm uses only a constant amount of extra space:
    * */
    int fractionalKnapsack(int weightLimit,Item[] arr,int size){
        for(int i=0;i<size;i++){
            int value = arr[i].value;
            int weight = arr[i].weight;
            arr[i].setValuePerWeight((float)value/weight);
        }

        Arrays.sort(arr);

        int totalProfit = 0;
        for(int i=0;i<size && weightLimit!=0;i++){
            Item item = arr[i];
            int requiredWright = item.weight;
            if (requiredWright<weightLimit){ // We can take the whole item.
                weightLimit = weightLimit - item.weight;
                totalProfit += item.value;
            } else { // We can't take the whole item,need to take in fractions.
                float fraction = (float) weightLimit/item.weight;
                totalProfit += item.value*fraction;
                weightLimit -= item.weight*fraction;
            }

        }
        return totalProfit;
    }

    public static void main(String[] args) {
        _4_FractionalKnapsackProblem obj = new _4_FractionalKnapsackProblem();
        int n = 3, weight = 50;
        Item arr[] = {new Item (100,20),new Item(60,10),new Item(120,30)};
        double ans = obj.fractionalKnapsack(weight, arr, n);
        System.out.println("Expected Output: 240.0");
        System.out.println("The maximum value is "+ans);

        // Use Case 1: All items fit completely
        int n1 = 3, weight1 = 50;
        Item arr1[] = {new Item(120, 10), new Item(80, 20), new Item(150, 20)};
        double ans1 = obj.fractionalKnapsack(weight1, arr1, n1);
        System.out.println("Use Case 1:");
        System.out.println("Expected Output: 350.0");
        System.out.println("The maximum value is " + ans1);
        System.out.println("--------------------");

        // Use Case 2: Fractional item needed
        int n2 = 4, weight2 = 40;
        Item arr2[] = {new Item(15, 5), new Item(20, 10), new Item(30, 15), new Item(10, 5)};
        double ans2 = obj.fractionalKnapsack(weight2, arr2, n2);
        System.out.println("Use Case 2:");
        System.out.println("Expected Output: 75.0"); // Corrected output
        System.out.println("The maximum value is " + ans2);
        System.out.println("--------------------");

        // Use Case 3: Larger, more complex scenario
        int n3 = 5, weight3 = 70;
        Item arr3[] = {new Item(50, 10), new Item(20, 30), new Item(15, 5), new Item(40, 20), new Item(30, 15)};
        double ans3 = obj.fractionalKnapsack(weight3, arr3, n3);
        System.out.println("Use Case 3:");
        System.out.println("Expected Output: 148.33333333333334"); // Corrected output
        System.out.println("The maximum value is " + ans3);
        System.out.println("--------------------");
    }

}
