#include <iostream>
#include <vector>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, cnt = 0;
    cin >> N;
    for(int i = 1; i <= N; i++){
        int tmp = i;
        if(tmp < 10){
            cnt++;
            continue;
        }
        vector<int> number;
        while(tmp != 0){
            number.push_back(tmp % 10);
            tmp -= number.back();
            tmp /= 10;
        }
        int gap = number[0] - number[1];
        bool flag = true;
        for(int j = 0; j < number.size() - 1; j++){
            if(number[j] - number[j + 1] != gap){
                flag = false;
                break;
            }
        }
        if(flag)
            cnt++;
    }
    cout << cnt;

    return 0;   
}