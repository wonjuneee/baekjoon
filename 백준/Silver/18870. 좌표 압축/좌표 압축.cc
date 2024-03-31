#include <bits/stdc++.h>
using namespace std;

bool compare(pair<int, int> p1, pair<int, int> p2){
    return p1.second < p2.second;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N;
    cin >> N;
    vector<pair<int, int>> points;
    for(int i = 0; i < N; i++){
        int tmp;
        cin >> tmp;
        points.push_back(pair(tmp, i));
    }
    sort(points.begin(), points.end());

    int count = 0;
    for (int i = 1; i < N; i++){
        if(points[i].first != points[i - 1].first)
            points[i - 1].first = count++;
        else
            points[i - 1].first = count;
    }
    points[N - 1].first = count;
    sort(points.begin(), points.end(), compare);

    for(pair<int, int> point: points){
        cout << point.first << ' ';
    }

    return 0;   
}