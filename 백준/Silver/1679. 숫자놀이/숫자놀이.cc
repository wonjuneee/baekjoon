#include <bits/stdc++.h>
using namespace std;

int n, k;
int numbers[1000];
int dp[50001]; // idx(숫자)를 만들 수 있는 숫자들의 최소 개수 

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    memset(numbers, 0, sizeof(numbers));

    cin >> n;
    for (int i = 0; i < n; i++)
        cin >> numbers[i];
    cin >> k;

    dp[1] = 1;

    for (int i = 2; i < 50001; i++){
        int mini = 51;
        for (int j = 1; j < i / 2 + 1; j++){
            mini = min(mini, dp[j] + dp[i - j]);
        }
        for (int number : numbers){
            if(number > i || number == 0)
                break;
            if (number == i){
                mini = 1;
                break;
            }
        }
        if(mini > k){
            if(i % 2 == 0){
                cout << "holsoon win at " << i;
                break;
            }
            else{
                cout << "jjaksoon win at " << i;
                break;
            }
        }
        dp[i] = mini;
    }
    return 0;
}