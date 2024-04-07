#include <bits/stdc++.h>
using namespace std;

vector<int> arr;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, K;
    cin >> N >> K;

    for (int i = 0; i < N; i++){
        int tmp;
        cin >> tmp;
        arr.push_back(tmp);
    }
    int sum = 0;
    for (int i = 0; i < K; i++){
        sum += arr[i];
    }
    int max = sum;
    for (int i = 0; i < N - K; i++){
        sum += (arr[i + K] - arr[i]);
        max = max > sum ? max : sum;
    }

    cout << max;

    return 0;
}