#include <iostream>
#include <memory.h>
using namespace std;

int n, m, a, b;
int arr[1000001];

int Find(int x){
    if(arr[x] == x)
        return x;
    else{
        arr[x] = Find(arr[x]);
        return arr[x];
    }
}

void Union(int x, int y){
    int first = Find(x);
    int second = Find(y);

    if(first == second)
        return;
    arr[first] = second;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    for (int i = 0; i <= n; i++)
        arr[i] = i; // Make Set

    for(int i = 0; i < m; i++){
        int tmp;
        cin >> tmp >> a >> b;
        if(!tmp)
            Union(a, b);
        else{
            int first = Find(a);
            int second = Find(b);
            if(first == second)
                cout << "YES" << '\n';
            else
                cout << "NO" << '\n';
        }
    }
    return 0;
}