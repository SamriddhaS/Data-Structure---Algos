package stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Problem Link : https://leetcode.com/problems/asteroid-collision/
 * Video Explanation : https://www.youtube.com/watch?v=LN7KjRszjk4
 *
 * 735. Asteroid Collision
 * We are given an array asteroids of integers representing asteroids in a row.
 * The indices of the asteriod in the array represent their relative position in space.
 *
 * For each asteroid, the absolute value represents its size, and the sign represents its
 * direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.
 *
 * Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
 * If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.
 *
 * Example 1:
 *
 * Input: asteroids = [5,10,-5]
 * Output: [5,10]
 * Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
 *
 *  Example 2:
 * Input: asteroids = [8,-8]
 * Output: []
 * Explanation: The 8 and -8 collide exploding each other.
 *
 * Example 3:
 * Input: asteroids = [10,2,-5]
 * Output: [10]
 * Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 *
 *
 * Constraints:
 * 2 <= asteroids.length <= 104
 * -1000 <= asteroids[i] <= 1000
 * asteroids[i] != 0
 *
 */
public class _11_AsteroidCollision {

    /**
     * Time Complexity: O(n)
     *
     * Each asteroid is pushed and popped at most once from the stack
     * The inner while loop processes each element a constant number of times across all iterations
     *
     * Space Complexity: O(n)
     *
     * Stack can hold up to n asteroids in worst case (all moving right)
     * Answer array requires O(n) space
     * Overall: O(n) auxiliary space
    * */
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int astroid : asteroids) {
            if (stack.isEmpty() ||
                    (stack.peek() > 0 && astroid > 0) // has same direction
                    || (stack.peek() < 0 && astroid < 0) // has same direction
            ) {
                stack.push(astroid);
            } else {
                int collidingAstroid = astroid;
                while (!stack.isEmpty() && (stack.peek() > 0 && collidingAstroid < 0)) { // collision is only possible if +direction is followed by an -direction.
                    int element = stack.pop();
                    if (Math.abs(element) > Math.abs(collidingAstroid)) { // element has a greater weight
                        collidingAstroid = element;
                    } else if (Math.abs(collidingAstroid) > Math.abs(element)) { // collidingAstroid has a greater weight
                        //collidingAstroid = astroid;
                    } else {
                        collidingAstroid = 0;
                    }
                }
                if (collidingAstroid != 0) stack.push(collidingAstroid); // push the survived astroid to the stack.
            }
        }
        int[] answer = new int[stack.size()];
        int i=stack.size()-1;
        while(!stack.isEmpty()){
            answer[i] = stack.pop();
            i--;
        }
        return answer;
    }

    /**
    * Same solution with a bit clear code.
    * */
    public int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int i=0;i<asteroids.length;i++) {
            int astroid = asteroids[i];
            while (!stack.isEmpty()&&stack.peek()>0&&astroid<0){
                int diff = stack.peek()+astroid;
                if (diff<0) stack.pop();
                else if (diff>0) astroid=0;
                else {
                    astroid=0;
                    stack.pop();
                }
            }

            if (astroid!=0) stack.push(astroid);
        }

        int[] answer = new int[stack.size()];
        int i=stack.size()-1;
        while(!stack.isEmpty()){
            answer[i] = stack.pop();
            i--;
        }
        return answer;
    }

    public static void main(String[] args) {
        _11_AsteroidCollision obj = new _11_AsteroidCollision();
        int[] nums2 = {5,10,-5};
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision(nums2)));
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision1(nums2)));
        int[] nums3 = {-2,-1,1,2};
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision(nums3)));
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision1(nums3)));
        int[] nums4 = {-2,-2,1,-2};
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision(nums4)));
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision1(nums4)));
        int[] nums5 = {-1,3,2,-3};
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision(nums5)));
        System.out.println("The Answer : " + Arrays.toString(obj.asteroidCollision1(nums5)));
    }
}