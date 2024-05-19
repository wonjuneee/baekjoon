#include <bits/stdc++.h>
using namespace std;

int n, m;
int arr[500][500];
int visited[500][500];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

int dfs(int x, int y, int sum, int size){
    if(size == 4) return sum;
    int maxi = sum;
    for (int i = 0; i < 4; i++){
        int tmp = sum;
        int xi = x + dx[i];
        int yi = y + dy[i];
        if(xi >= 0 && xi < m && yi >= 0 && yi < n){
            if(arr[xi][yi] && !visited[xi][yi]){
                tmp += arr[xi][yi];
                visited[xi][yi] = 1;
                maxi = max(maxi, dfs(xi, yi, tmp, size + 1));
                visited[xi][yi] = 0;
            }
        }
    }
    return maxi;
}

int centroid(int x, int y, int sum){
    int mini = 1001, center = sum, size = 1;
    for (int i = 0; i < 4; i++){
        int xi = x + dx[i];
        int yi = y + dy[i];
        if(xi < 0 || xi >= m || yi < 0 || yi >= n) continue;
        size++;
        center += arr[xi][yi];
        mini = min(mini, arr[xi][yi]);
    }
    if(size > 4) return center - mini;
    else
        return center;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(NULL);

    memset(arr, 0, sizeof(arr));
    memset(visited, 0, sizeof(visited));
    int result = 0;
    cin >> n >> m;
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++){
            cin >> arr[j][i];
        }
    }
    for (int i = 0; i < n; i++){
        for (int j = 0; j < m; j++){
            visited[j][i] = 1;
            int maxi = dfs(j, i, arr[j][i], 1);
            visited[j][i] = 0;
            result = max(maxi, result);
            maxi = centroid(j, i, arr[j][i]);
            result = max(maxi, result);
        }
    }
    cout << result;
    return 0;
}