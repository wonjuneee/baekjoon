#include <bits/stdc++.h>
using namespace std;

vector<int> arr;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N, M;
    cin >> N >> M;
    string S;
    cin >> S;

    int count = 0, result = 0;
    int size = 2 * N + 1;
    count = 0;
    for (int start = 0, end = 0; end <= M;){
        int gap = end - start + 1;
        if(gap <= size && end != M){
            if(count % 2 == 0 && S[end] == 'I'){
                end++;
                count++;
            }
            else if(count % 2 == 1 && S[end] == 'O'){
                end++;
                count++;
            }
            else{
                if(start == end){
                    start++;
                    end++;
                }
                start = end;
                count = 0;
            }
        }
        else{
            if(count == size){
                result++;
                count -= 2;
                start += 2;
            }
            if(end == M)
                break;
        }
    }
    cout << result;
    return 0;
}