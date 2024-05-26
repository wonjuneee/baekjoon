#include <bits/stdc++.h>
using namespace std;

int n;
pair<int, int> arr[100001];

bool compare(pair<int, int> a, pair<int, int> b){
    if(a.second < b.second)
        return true;
    else if(a.second == b.second)
        return a.first < b.first;
    else
        return false;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    memset(arr, 0, sizeof(arr));

    cin >> n;
    for (int i = 1; i <= n; i++){
        int start, end;
        cin >> start >> end;
        arr[i] = make_pair(start, end);
    }
    sort(arr, arr + (n + 1), compare);

    int count = 0, time = 0;
    for (int i = 1; i <= n; i++){
        if(time <= arr[i].first){
            count++;
            time = arr[i].second;
        }
    }

    cout << count;

    return 0;
}