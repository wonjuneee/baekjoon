#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int N, sum = 0;
    cin >> N;
    vector<int> number;
    int count[8001] = {0};
    for(int i = 0; i < N; i++){
        int tmp;
        cin >> tmp;
        sum += tmp;
        number.push_back(tmp);
        if(tmp < 0)
            count[8001 + tmp]++;
        else
            count[tmp]++;
    }
    int result1 = round((float)sum / N);
    sort(number.begin(), number.end());
    int result2 = number[N / 2];
    int max = 0, cnt = 0, manyMaxVal = 0, idxBefore, idx, result3;
    for(int i = 4000; cnt != N; i--){
        if(i == -1)
            i = 8000;
        if(count[i] != 0)
            cnt += count[i];
        else
            continue;
        if(max == count[i]){
            idxBefore = idx;
            idx = i;
            manyMaxVal = 1;
        }
        if(max < count[i]){
            max = count[i];
            idx = i;
            manyMaxVal = 0;
        }
    }
    if(manyMaxVal)
        if(idxBefore > 4000)
            result3 = -(8001 - idxBefore);
        else
            result3 = idxBefore;
    else
        if(idx > 4000)
            result3 = -(8001 - idx);
        else
            result3 = idx;
    int result4 = number[N - 1] - number[0];
    cout << result1 << '\n' << result2 << '\n' << result3 << '\n' << result4;

    return 0;
}