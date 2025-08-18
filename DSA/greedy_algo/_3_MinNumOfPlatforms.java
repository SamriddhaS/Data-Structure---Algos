package DSA.greedy_algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Problem Link : https://takeuforward.org/data-structure/minimum-number-of-platforms-required-for-a-railway/
 * Solution Explanation : https://www.youtube.com/watch?v=AsGzwR_FWok
 *
 * Minimum number of platforms required for a railway
 *
 * Problem Statement: We are given two arrays that represent the arrival and departure times of trains that
 * stop at the platform. We need to find the minimum number of platforms needed at the railway station so
 * that no train has to wait.
 *
 * Examples 1:
 * Input: N=6,
 * arr[] = {9:00, 9:45, 9:55, 11:00, 15:00, 18:00}
 * dep[] = {9:20, 12:00, 11:30, 11:50, 19:00, 20:00}
 *
 * Output:3
 *
 * Explanation: There are at-most three trains at a time. The train at 11:00 arrived but the trains which had arrived at 9:45 and 9:55 have still not departed. So, we need at least three platforms here.
 *
 * Example 2:
 * Input Format: N=2,
 * arr[]={10:20,12:00}
 * dep[]={10:50,12:30}
 *
 * Output: 1
 *
 */
public class _3_MinNumOfPlatforms {

    static class Train {
        int arrivalTime;
        int departureTime;
        Train(int arrivalTime,int departureTime){
            this.arrivalTime = arrivalTime;
            this.departureTime = departureTime;
        }
    }

    static class Platform {
        int platformNo;
        int freeAt;
        Platform(int platformNo,int freeAt){
            this.platformNo =  platformNo;
            this.freeAt = freeAt;
        }

        public void setFreeAt(int freeAt) {
            this.freeAt = freeAt;
        }
    }

    /**
     * Brute force approach.
     *
     * When a new train arrives we check all the platforms for availability.
     * If any platform is available we don't add a new platform.
     * If no platform is available on that time we create a new platform that increases the total platform count.
     *
     * Time Complexity: O(n²)
     * The algorithm has nested loops:
     * Outer loop: Iterates through all n trains → O(n)
     * Inner while loop: For each train, it searches through all existing platforms to find an available one
     * In the worst case, this searches through all platforms created so far
     * Since we might need up to n platforms (if no trains can share platforms), this inner search can be O(n)
     *
     * Overall: O(n) × O(n) = O(n²)
     *
     *
     * Space Complexity: O(n)
     *
     * The algorithm uses:
     *
     * trainsList: Stores n Train objects → O(n)
     * allPlatforms: In worst case, stores up to n Platform objects → O(n)
     * Other variables are constant space → O(1)
     *
     * Total: O(n)
     *
    * */
    public int countPlatforms(int n,int arr[],int dep[]){

        // Create list of all the trains.
        ArrayList<Train> trainsList = new ArrayList<>();
        for(int i=0;i<n;i++){
            trainsList.add(new Train(arr[i],dep[i]));
        }

        ArrayList<Platform> allPlatforms = new ArrayList<>();
        // Check all the train one by one.
        for (int i=0;i<n;i++){
            Train currentTrain = trainsList.get(i);
            if (i==0){
                // For 0th no need to check just add a new platform.
                Platform platform = new Platform(i,currentTrain.departureTime);
                allPlatforms.add(platform);
            } else {

                int newTrainArrivalTime = currentTrain.arrivalTime;
                int p = 0;
                boolean needNewPlatform = true;
                // For 0th no need to check just add a new platform.
                while (p<allPlatforms.size()){
                    Platform platform = allPlatforms.get(p);
                    // Check if any platform is available -> we don't add a new platform in that case.
                    if (newTrainArrivalTime>platform.freeAt){
                        needNewPlatform = false;
                        platform.setFreeAt(currentTrain.departureTime);
                        break;
                    }
                    p++;
                }

                if (needNewPlatform){
                    // If all the platforms are full create a new platform.
                    Platform platform = new Platform(i,currentTrain.departureTime);
                    allPlatforms.add(platform);
                }
            }
        }

        System.out.println("No of platforms needed : "+allPlatforms.size());
        return 0;
    }

    /**
     * Better than the brute force approach as it uses O(1) space.
     *
     * Time Complexity: O(n²)
     *
     * Outer loop: Runs n times
     * Inner loop: Runs (n-1) + (n-2) + ... + 1 = n(n-1)/2 times total
     * Each comparison: O(1) - direct array access and arithmetic operations
     * Total: Still O(n²)
     *
     * Space Complexity: O(1)
     * */
    public int countPlatformsBetter(int n,int arr[],int dep[]){

        int maxPlatforms = 0;
        for (int i=0;i<n;i++){

            int currentArrival = arr[i];
            int currentDeparture = dep[i];

            int currentPlatformCount = 1;
            for (int j=i+1;j<n;j++){

                int nextArrival = arr[j];
                int nextDeparture = dep[j];

                if (Math.max(currentArrival, nextArrival) <=
                        Math.min(currentDeparture, nextDeparture))
                {
                    // next arrival > current arrival && next arrival <= current departure
                    // next arrival <= current arrival && next departure >= current arrival
                    currentPlatformCount++;
                }
            }
            maxPlatforms = Math.max(currentPlatformCount,maxPlatforms);
        }

        System.out.println("Platform Count : "+maxPlatforms);
        return maxPlatforms;
    }

    /**
    * Time Complexity: O(n log n)
     * The time complexity is dominated by the sorting operations:
     *
     * Arrays.sort(arr) takes O(n log n)
     * Arrays.sort(dep) takes O(n log n)
     * The while loop runs at most n iterations (since arrivalPointer goes from 0 to n-1), making it O(n)
     *
     * Therefore: O(n log n) + O(n log n) + O(n) = O(n log n)
     *
     *
     * Space Complexity: O(1)
     * The space complexity is constant because:
    * */
    public int countPlatformsOptimal(int n,int arr[],int dep[]){

        Arrays.sort(arr);
        Arrays.sort(dep);
        int maxCount = 0;
        int currentCount = 0;
        int departurePointer = 0;
        int arrivalPointer = 0;
        while (arrivalPointer<n){
            if (arr[arrivalPointer]<=dep[departurePointer]){
                arrivalPointer++;
                currentCount++;
            }else {
                departurePointer++;
                currentCount--;
            }
            maxCount = Math.max(currentCount,maxCount);
        }
        System.out.println("Platform Count : "+maxCount);
        return maxCount;

    }



    public static void main(String[] args) {
        _3_MinNumOfPlatforms obj = new _3_MinNumOfPlatforms();
        int[] arr ={900,945,955,1100,1500,1800};
        int[] dep={920,1200,1130,1150,1900,2000};
        int n=arr.length;
        obj.countPlatforms(n,arr,dep);

        int[] arr1 ={1020,1200};
        int[] dep1={1050,1230};
        obj.countPlatforms(arr1.length,arr1,dep1);

        obj.countPlatformsBetter(n,arr,dep);
        obj.countPlatformsBetter(arr1.length,arr1,dep1);

        obj.countPlatformsOptimal(n,arr,dep);
        obj.countPlatformsOptimal(arr1.length,arr1,dep1);
    }

}
