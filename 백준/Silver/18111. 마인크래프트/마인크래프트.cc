#include <iostream>
using namespace std;

bool cmp(int a, int b){
    if(a > b) return a <= b;
    return true;
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, M, B, min = 256, max = 0;
    cin >> N >> M >> B;
    int grid[500][500];
    for(int i = 0; i < N; i++){
        for(int j = 0; j < M; j++){
            int tmp;
            cin >> tmp;
            grid[i][j] = tmp;
            if(max < tmp)
                max = tmp;
            if(min > tmp)
                min = tmp;
        }
    } // 각 좌표별 쌓여있는 블럭 및 최대, 최소 높이 확인
    int timeEachFloor[max - min + 1][2] = {0, 0};
    // (min층에서부터 쌓아서 idx층이 되는 시간, max층에서부터 부셔서 idx층이 되는 시간)
    for(int i = 0; i < max - min + 1; i++){
        if(i == 0){
            timeEachFloor[i][0] = 0;
            continue;
        }
        timeEachFloor[i][0] = timeEachFloor[i - 1][0];
        for(int j = 0; j < N; j++){
            for(int k = 0; k < M; k++){
                if(grid[j][k] < min + i)
                    timeEachFloor[i][0]++;
            }
        }
    } // 쌓아올리는 시간
    for(int i = max - min; i >= 0; i--){
        if(i == max - min){
            timeEachFloor[i][1] = 0;
            continue;
        }
        timeEachFloor[i][1] = timeEachFloor[i + 1][1];
        for(int j = 0; j < N; j++){
            for(int k = 0; k < M; k++){
                if(grid[j][k] > min + i)
                    timeEachFloor[i][1]++;
            }
        }
    }
    int time[max - min + 1];
    for(int i = 0; i < max - min + 1; i++){
        timeEachFloor[i][1] *= 2;
        time[i] = timeEachFloor[i][0] + timeEachFloor[i][1];
    } // 부시는 시간
    int t = time[0], floor = 0;
    for(int i = 0; i < max - min + 1; i++){
        if(B + (timeEachFloor[i][1] / 2) >= timeEachFloor[i][0]){
            if(t >= time[i]){
                t = time[i];
                floor = i;
            }
        }
    } // 아래층에서부터 최소시간 및 최소시간 소요 최대층 갱신
    cout << t << ' ' << floor + min;

    return 0;   
}