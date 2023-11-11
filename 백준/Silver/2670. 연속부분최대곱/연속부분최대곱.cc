#include <iostream>
#include <algorithm>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int n;
    cin >> n;
    double number[n], dp[n];
    for(int i = 0; i < n; i++){
        double tmp;
        cin >> tmp;
        number[i] = tmp;
    }
    dp[0] = number[0];
    for(int i = 1; i < n; i++){
        dp[i] = max(number[i], dp[i - 1] * number[i]);
    }
    sort(dp, dp + n);

    cout << fixed;
    cout.precision(3);
    cout << dp[n - 1];

    return 0;
}