#include <iostream>
using namespace std;

int n, m, u, v;
int root[200001];
int cnt[200001];  // 각 집합의 크기를 저장

int Find(int x){
    if (root[x] == x)
        return x;
    else{
        root[x] = Find(root[x]);
        return root[x];
    }
}

void Union(int x, int y){
    int first = Find(x);
    int second = Find(y);

    if(first == second)
        return;

    if (cnt[first] < cnt[second]) {
        root[first] = second;
        cnt[second] += cnt[first];
    }
    else{
        root[second] = first;
        cnt[first] += cnt[second];
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> n >> m;
    for (int i = 1; i <= n; i++){
        root[i] = i;
        cnt[i] = 1;
    }

    while(m--){
        cin >> u >> v;
        Union(u, v);
    }
    
    long long result = 1;
    for (int i = 1; i <= n; i++){
        if(root[i] == i){
            result = (result * cnt[i]) % 1000000007;
        }
    }
    cout << result;

    return 0;
}