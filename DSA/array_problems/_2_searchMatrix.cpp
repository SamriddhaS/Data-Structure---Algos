#include <bits/stdc++.h>
using namespace std;

bool binarySearch(vector<int>& nums, int target) {
    int n = nums.size(); //size of the array
    int low = 0, high = n - 1;

    // Perform the steps:
    while (low <= high) {
        int mid = (low + high) / 2;
        if (nums[mid] == target) return true;
        else if (target > nums[mid]) low = mid + 1;
        else high = mid - 1;
    }
    return false;
}

bool searchMatrix(vector<vector<int>>& matrix, int target) {
        int n = matrix.size();
        int m = matrix[0].size();
        for (int i=0;i<n;i++){
            if (target>=matrix[i][0] && target <= matrix[i][m-1])
            {
                return binarySearch(matrix[i],target);
            }
        }
        return false;
}

// Driver code
int main()
{
    //vector<vector<int>> matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
    vector<vector<int>> matrix = {{-10}, {7}, {5}};
    
    int target = -10;
    bool output = searchMatrix(matrix,target);

    cout << "\n" << target <<" is present : \n" ;
    cout << output ;

    return 0;
}