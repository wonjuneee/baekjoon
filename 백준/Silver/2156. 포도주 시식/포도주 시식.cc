#include <bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int n;
    cin >> n;
    int wine[10001], dp[10001];
    for (int i = 1; i < n + 1; i++){
        int tmp;
        cin >> tmp;
        wine[i] = tmp;
    }
    dp[0] = wine[0] = 0;
    dp[1] = wine[1];
    dp[2] = wine[1] + wine[2];
    for (int i = 3; i <= n; i++){
        // dp[i-3] + wine[i-1] + wine[i] or dp[i-2] + wine[i]
        // or dp[i-1]
        int tmp = dp[i - 3] + wine[i - 1] + wine[i] > dp[i - 2] + wine[i]
                      ? dp[i - 3] + wine[i - 1] + wine[i]
                      : dp[i - 2] + wine[i];
        dp[i] = tmp > dp[i - 1] ? tmp : dp[i - 1];
    }
    cout << dp[n];

    return 0;
}