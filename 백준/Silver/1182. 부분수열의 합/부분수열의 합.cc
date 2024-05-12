#include <bits/stdc++.h>
using namespace std;

int n, s;
int seq[20], result = 0;

void subSeq(int sum, int idx){
    sum += seq[idx];
    if(sum == s)
        result++;
    for (int i = idx + 1; i < n; i++){
        subSeq(sum, i);
    }
}

int main(){
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    cin >> n >> s;
    for (int i = 0; i < n; i++){
        int tmp;
        cin >> tmp;
        seq[i] = tmp;
    }

    for (int i = 0; i < n; i++)
        subSeq(0, i);

    cout << result;
    return 0;
}