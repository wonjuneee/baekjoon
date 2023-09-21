#include <iostream>
#include <vector>
using namespace std;


int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, K, idx = -1;
    cin >> N >> K;
    int eliminated[N] = {0};
    vector<int> order;
    while(order.size() != N){
        int count = 1;
        if(idx == N){
            idx = 0;
        }
        idx++;
        while(count < K){
            if(idx == N)
                idx = 0;
            if(eliminated[idx] == 0){
                idx++;
                count++;
            }
            else{
                idx++;
            }
        }
        while(eliminated[idx] == 1 || idx >= N){
            if(idx >= N - 1)
                idx = 0;
            else
                idx++;
        }
        order.push_back(idx + 1);
        eliminated[idx] = 1;
    }
    cout << '<';
    for(int i = 0; i < order.size(); i++){
        cout << order[i];
        if(i != order.size() - 1)
            cout << ", ";
    } cout << '>';


    return 0;
}