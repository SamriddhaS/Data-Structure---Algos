package DSA.greedy_algo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem Link : https://takeuforward.org/data-structure/job-sequencing-problem/
 * Solution concept explanation : https://www.youtube.com/watch?v=zPtI8q9gvX8&list=PLDN4rrl48XKpZkf03iYFl-O29szjTrs_O&index=42
 * Solution Explanation : https://www.youtube.com/watch?v=AsGzwR_FWok
 *
 * Job Sequencing Problem
 *
 * Problem Statement: You are given a set of N jobs where each job comes with a deadline and profit.
 * The profit can only be earned upon completing the job within its deadline. Find the number of jobs
 * done and the maximum profit that can be obtained. Each job takes a single unit of time and only one
 * job can be performed at a time.
 *
 * Examples
 *
 * Example 1:
 * Input: N = 4, Jobs = {(1,4,20),(2,1,10),(3,1,40),(4,1,30)}
 * Output: 2 60
 *
 * Explanation: The 3rd job with a deadline 1 is performed during the first unit of time .
 * The 1st job is performed during the second unit of time as its deadline is 4.
 *
 * Profit = 40 + 20 = 60
 *
 * Example 2:
 * Input: N = 5, Jobs = {(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
 *
 * Output: 2 127
 *
 * Explanation: The  first and third job both having a deadline 2 give the highest profit.
 * Profit = 100 + 27 = 127
 *
 */
public class _2_JobSequencingProblem {

    static class Job implements Comparable<Job> {

        int profit;
        int deadline;
        int id;

        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            return Integer.compare(this.profit, o.profit);
        }

    }

    /**
     * Solution after 1st Iteration.
     */
    void maxJobs(ArrayList<Job> allJobs, int size) {

        int maximumSlots = 0;

        // Find the maximum slots that we can take.
        for (int i = 0; i < size; i++) {
            maximumSlots = Math.max(allJobs.get(i).deadline, maximumSlots);
        }

        boolean[] slots = new boolean[maximumSlots];
        for (int i = 0; i < maximumSlots; i++) {
            slots[i] = true;
        }

        allJobs.sort(Collections.reverseOrder());

        ArrayList<Integer> answer = new ArrayList<>();
        int maxProfit = 0;
        int currentDeadline = Integer.MIN_VALUE;

        for (int i = 0; i < size; i++) {
            Job job = allJobs.get(i);
            if (i == 0) {
                // If first job then directly add it as all the slots are available.
                int jobDeadline = job.deadline;
                answer.add(job.id);
                currentDeadline = job.deadline;
                maxProfit += job.profit;
                slots[jobDeadline - 1] = false;
            } else {
                if (job.deadline >= currentDeadline) {
                    // 2 conditions before picking a new job.
                    // 1 -> currentDeadline < job's deadline.
                    // 2 -> slots are available on/before that deadline.
                    int jobDeadline = job.deadline;
                    while (jobDeadline != 0) {
                        if (slots[jobDeadline - 1]) { // Slot is available
                            answer.add(job.id);
                            currentDeadline = job.deadline;
                            maxProfit += job.profit;
                            slots[jobDeadline - 1] = false;
                            break;
                        }
                        jobDeadline--;
                    }
                }
            }

        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.println("Jobs Done(Id) : [" + answer.get(i) + "]");
        }

        System.out.println("Total Profit : " + maxProfit);
    }

    /**
     * 2nt Iteration : Without the (job.deadline >= currentDeadline) checking as we already have
     * slots array to track the same thing.
     *
     * Time Complexity: O(n × m)
     * Where:
     * n = number of jobs (size)
     * m = maximum deadline among all jobs
     *
     * Breakdown:
     * Finding maximum deadline: O(n)
     * Initializing slots array: O(m)
     * Sorting jobs: O(n log n)
     * Main scheduling loop: O(n × m) in worst case
     *
     * For each job (n iterations)
     * Inner while loop can run up to m times (when searching from deadline down to 1)
     *
     * Space Complexity: O(m + n)
     * Breakdown:
     * Slots boolean array: O(m) space
     * answer ArrayList: O(n) space in worst case (if all jobs are scheduled)
     * Other variables: O(1)
     *
     * So total space complexity is O(m + n).
     */
    void maxJobsTwo(ArrayList<Job> allJobs, int size) {

        int maximumSlots = 0;

        // Find the maximum slots that we can take.
        for (int i = 0; i < size; i++) {
            maximumSlots = Math.max(allJobs.get(i).deadline, maximumSlots);
        }

        boolean[] slots = new boolean[maximumSlots];
        for (int i = 0; i < maximumSlots; i++) {
            slots[i] = true;
        }

        allJobs.sort(Collections.reverseOrder());

        ArrayList<Integer> answer = new ArrayList<>();
        int maxProfit = 0;

        for (int i = 0; i < size; i++) {
            Job job = allJobs.get(i);
            // 2 -> slots are available on/before that deadline.
            int jobDeadline = job.deadline;
            while (jobDeadline != 0) {
                if (slots[jobDeadline - 1]) { // Slot is available
                    answer.add(job.id);
                    maxProfit += job.profit;
                    slots[jobDeadline - 1] = false;
                    break;
                }
                jobDeadline--;
            }

        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.println("Jobs Done(Id) : [" + answer.get(i) + "]");
        }

        System.out.println("Total Profit : " + maxProfit);
    }

    public static void main(String[] args) {

        _2_JobSequencingProblem obj = new _2_JobSequencingProblem();

        ArrayList<Job> arr1 = new ArrayList<>();
        arr1.add(new Job(1, 4, 20));
        arr1.add(new Job(2, 1, 10));
        arr1.add(new Job(3, 1, 40));
        arr1.add(new Job(4, 1, 30));

        obj.maxJobsTwo(arr1, arr1.size());

        ArrayList<Job> arr = new ArrayList<>();
        arr.add(new Job(1, 4, 20));
        arr.add(new Job(2, 1, 10));
        arr.add(new Job(3, 2, 40));
        arr.add(new Job(4, 2, 30));

        obj.maxJobsTwo(arr, arr.size());

        //{(1,2,100),(2,1,19),(3,2,27),(4,1,25),(5,1,15)}
        ArrayList<Job> arr3 = new ArrayList<>();
        arr3.add(new Job(1, 2, 100));
        arr3.add(new Job(2, 1, 19));
        arr3.add(new Job(3, 2, 27));
        arr3.add(new Job(4, 2, 25));
        arr3.add(new Job(5, 1, 15));

        obj.maxJobs(arr3, arr3.size());

    }

}
