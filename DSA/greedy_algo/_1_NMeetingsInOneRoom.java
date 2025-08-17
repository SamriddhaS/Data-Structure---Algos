package DSA.greedy_algo;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Problem Link : https://leetcode.com/problems/delete-node-in-a-linked-list/description/
 * Explanation : https://www.youtube.com/watch?v=icnp4FJdZ_c&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=32
 *
 *
* */

public class _1_NMeetingsInOneRoom  {

    class Meeting implements Comparable<Meeting>{
        int startTime;
        int endTime;
        int index;
        Meeting(){

        }

        Meeting(int startTime,int endTime,int index){
            this.startTime = startTime;
            this.endTime = endTime;
            this.index = index;
        }

        @Override
        public int compareTo(Meeting o) {
            return Integer.compare(this.endTime, o.endTime);
        }
    }

    void maxMeetings(int[] startTimes, int[] endTimes,int size){
        ArrayList<Meeting> allMeetings = new ArrayList<>();
        for (int i = 0;i<size;i++){
            Meeting meeting = new Meeting(startTimes[i],endTimes[i],i+1);
            allMeetings.add(meeting);
        }

        Collections.sort(allMeetings);

        ArrayList<Integer> answer = new ArrayList<>();
        int limit = -1;

        for (int i = 0;i<size;i++){
            Meeting meeting = allMeetings.get(i);

            if(meeting.startTime<limit) continue;
            answer.add(meeting.index);
            limit = meeting.endTime;
        }

        Collections.sort(answer);

        for (int i = 0;i<answer.size();i++){
            System.out.println("Answers : "+answer.get(i));
        }
    }

    public static void runAllTests() {
        _1_NMeetingsInOneRoom obj = new _1_NMeetingsInOneRoom();

        // Test Case 1: Original example
        System.out.println("Test Case 1 - Original Example:");
        int n1 = 6;
        int start1[] = {1,3,0,5,8,5};
        int end1[] = {2,4,5,7,9,9};
        System.out.println("Expected: 1 2 4 5");
        obj.maxMeetings(start1, end1, n1);
        System.out.println();

        // Test Case 2: All meetings overlap
        System.out.println("Test Case 2 - All meetings overlap:");
        int n2 = 4;
        int start2[] = {1, 2, 3, 4};
        int end2[] = {5, 6, 7, 8};
        System.out.println("Expected: 1");
        obj.maxMeetings(start2, end2, n2);
        System.out.println();

        // Test Case 3: No overlaps - all meetings can be scheduled
        System.out.println("Test Case 3 - No overlaps:");
        int n3 = 5;
        int start3[] = {1, 3, 5, 7, 9};
        int end3[] = {2, 4, 6, 8, 10};
        System.out.println("Expected: 1 2 3 4 5");
        obj.maxMeetings(start3, end3, n3);
        System.out.println();

        // Test Case 4: Edge case - single meeting
        System.out.println("Test Case 4 - Single meeting:");
        int n4 = 1;
        int start4[] = {5};
        int end4[] = {10};
        System.out.println("Expected: 1");
        obj.maxMeetings(start4, end4, n4);
        System.out.println();

        // Test Case 5: Meetings with same start times
        System.out.println("Test Case 5 - Same start times:");
        int n5 = 4;
        int start5[] = {1, 1, 1, 1};
        int end5[] = {3, 4, 5, 2};
        System.out.println("Expected: 4");
        obj.maxMeetings(start5, end5, n5);
        System.out.println();

        // Test Case 6: Meetings with same end times
        System.out.println("Test Case 6 - Same end times:");
        int n6 = 3;
        int start6[] = {1, 2, 3};
        int end6[] = {5, 5, 5};
        System.out.println("Expected: 1");
        obj.maxMeetings(start6, end6, n6);
        System.out.println();

        // Test Case 7: Point meetings (start = end)
        System.out.println("Test Case 7 - Point meetings:");
        int n7 = 4;
        int start7[] = {1, 2, 3, 2};
        int end7[] = {1, 2, 3, 3};
        System.out.println("Expected: 1 2 3");
        obj.maxMeetings(start7, end7, n7);
        System.out.println();

        // Test Case 8: Complex overlapping scenario
        System.out.println("Test Case 8 - Complex overlapping:");
        int n8 = 8;
        int start8[] = {0, 1, 1, 3, 5, 5, 8, 8};
        int end8[] = {2, 2, 3, 4, 6, 7, 9, 10};
        System.out.println("Expected: 1 4 5 7");
        obj.maxMeetings(start8, end8, n8);
        System.out.println();

        // Test Case 9: Large time gaps
        System.out.println("Test Case 9 - Large time gaps:");
        int n9 = 3;
        int start9[] = {1, 100, 200};
        int end9[] = {50, 150, 250};
        System.out.println("Expected: 1 2 3");
        obj.maxMeetings(start9, end9, n9);
        System.out.println();

        // Test Case 10: Reverse sorted by end time
        System.out.println("Test Case 10 - Reverse sorted:");
        int n10 = 4;
        int start10[] = {8, 6, 2, 1};
        int end10[] = {8, 7, 6, 5};
        System.out.println("Expected: 1 2 4");
        obj.maxMeetings(start10, end10, n10);
        System.out.println();
    }

    public static void main(String[] args) {
        runAllTests();
    }

}
