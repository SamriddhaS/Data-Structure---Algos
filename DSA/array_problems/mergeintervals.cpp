#include <iostream>
#include <vector>
#include "stdc++.h"
using namespace std;

vector<vector<int>> mergeOverlappingIntervals(vector<vector<int>> &intervals) {
    sort(intervals.begin(),intervals.end());
        int n = intervals.size();
        vector<vector<int>> answer;
        int ans = -1;
        bool needToMerge = false;
        for (int i = 0; i < n; i++) {
            cout << "[" << i << "] ";
            int start = intervals[i][0];
            int end = intervals[i][1];
            if(i==n-1){
                if(needToMerge){
                    int oldStart = answer[ans][0];
                    answer.insert(answer.begin()+ans, {oldStart,end});
                    needToMerge=false;
                }else{
                    answer.push_back({start,end});
                }
            }else{
                int nextStart = answer[i+1][0];
                if(end>nextStart){
                    if(!needToMerge){
                        ans = answer.size();
                        answer.push_back({start});
                    }
                    needToMerge = true;
                }else{
                    if(needToMerge){
                        int oldStart = answer[ans][0];
                        answer.insert(answer.begin()+ans, {oldStart,end});
                        needToMerge=false;
                    }else{
                        answer.push_back({start,end});
                    }
                }

            }
        }
        return answer;
}

int main()
{
    vector<vector<int>> arr = {{1, 3}, {8, 10}, {2, 6}, {15, 18}};
    vector<vector<int>> ans = mergeOverlappingIntervals(arr);
    cout << "The merged intervals are: " << "\n";
    for (auto it : ans) {
        cout << "[" << it[0] << ", " << it[1] << "] ";
    }
    cout << endl;
    return 0;
}