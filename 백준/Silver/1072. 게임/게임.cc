#include <bits/stdc++.h>
using namespace std;

int main(){
    long long x, y, z;
    cin >> x >> y;
    z = (100 * y) / x;

    if(z >= 99){
        cout << -1;
        return 0;
    }

    int left = 0, right = 1000000000, result = -1;
    while(left <= right){
        long long mid = (left + right) / 2;
        int rate = 100 * (y + mid) / (x + mid);
        if(rate > z){
            result = mid;
            right = mid - 1;
        }
        else if(rate <= z){
            left = mid + 1;
        }
    }
    cout << result;
    return 0;
}