#include <iostream>
#include <vector>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int K, N, sum;
    unsigned int length;
    cin >> K >> N;
    vector<int> cable;
    for(int i = 0; i < K; i++){
        int tmp;
        cin >> tmp;
        cable.push_back(tmp);
        length += tmp;
    }
    unsigned int left = 1, right = 2147483647;
    while(left <= right){
        length = (left + right) / 2;
        sum = 0;
        for(int i = 0; i < K; i++){
            sum += cable[i] / length;
        }
        if(sum < N)
            right = length - 1;
        else
            left = length + 1;
    }
    sum = 0;
    for(int i = 0; i < K; i++){
        sum += cable[i] / length;
    }
    if(sum < N)
        length--;
    cout << length;

    return 0;   
}
