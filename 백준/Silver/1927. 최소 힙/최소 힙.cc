#include <bits/stdc++.h>
using namespace std;

int n, x;
unsigned int arr[100000];

void heap(int idx){
    arr[idx] = x;
    while(idx > 0){
        if((idx - 1) / 2 >= 0 && arr[(idx - 1) / 2] > arr[idx]){
            swap(arr[idx], arr[(idx - 1) / 2]);
            idx = (idx - 1) / 2;
        }
        else{
            return;
        }
    }
}

void heapify(int idx, int heapSize){
    int smallest = idx;
    int left = (2 * idx) + 1;
    int right = (2 * idx) + 2;
    if(left < heapSize && arr[left] < arr[smallest])
        smallest = left;
    if(right < heapSize && arr[right] < arr[smallest])
        smallest = right;
    if(smallest != idx){
        swap(arr[idx], arr[smallest]);
        heapify(smallest, heapSize);
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    cin >> n;
    int max = (2 * n > 100000) ? 100000 : 2 * n;
    for (int i = 0; i < max; i++){
        arr[i] = 1U << 31;
    }

    int idx = -1;
    for (int i = 0; i < n; i++){
        cin >> x;
        if(x == 0){
            if (arr[0] == (1U << 31))
                cout << 0 << '\n';
            else{
                cout << arr[0] << '\n';
                swap(arr[0], arr[idx]);
                arr[idx] = 1U << 31;
                heapify(0, idx--);
            }
        }
        else
            heap(++idx);
    }

    return 0;
}