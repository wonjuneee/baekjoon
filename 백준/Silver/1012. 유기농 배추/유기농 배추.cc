#include <bits/stdc++.h>
using namespace std;

int t, n, m, k, x, y;
int arr[50][50];
int visited[50][50];

void dfs(int x, int y){
    visited[x][y] = 1;
    for (int i = 0; i < 4; i++){
        switch (i){
        case 0: // 상
            if(y - 1 < 0) continue;
            if(arr[x][y - 1] && !visited[x][y - 1])
                dfs(x, y - 1);
            break;
        case 1: // 하
            if(y + 1 >= n) continue;
            if(arr[x][y + 1] && !visited[x][y + 1])
                dfs(x, y + 1);
        case 2: // 좌
            if(x - 1 < 0) continue;
            if(arr[x - 1][y] && !visited[x - 1][y])
                dfs(x - 1, y);
            break;
        case 3: // 우
            if(x + 1 >= m) continue;
            if(arr[x + 1][y] && !visited[x + 1][y])
                dfs(x + 1, y);
            break;
        default:
            break;
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    cin >> t;
    for (int i = 0; i < t; i++){
        memset(arr, 0, sizeof(arr));
        memset(visited, 0, sizeof(visited));
        int result = 0;
        cin >> m >> n >> k;
        for (int j = 0; j < k; j++){
            cin >> x >> y;
            arr[x][y] = 1;
        }
        for (int j = 0; j < m; j++){
            for (int l = 0; l < n; l++){
                if(arr[j][l] && !visited[j][l]){
                    dfs(j, l);
                    result++;
                }
            }
        }
        cout << result << '\n';
    }
    return 0;
}