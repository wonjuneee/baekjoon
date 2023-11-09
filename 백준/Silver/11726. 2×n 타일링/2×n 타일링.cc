#include <iostream>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int n;
    cin >> n;
    int block[n];
    block[0] = 1, block[1] = 2;
    for(int i = 2; i < n; i++)
        block[i] = (block[i - 1] + block[i - 2]) % 10007;
    cout << block[n - 1];

    return 0;
}