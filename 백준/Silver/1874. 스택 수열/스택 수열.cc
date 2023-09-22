#include <iostream>
#include <queue>
#include <stack>
using namespace std;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    int n, cnt = 0;
    cin >> n;
    int arr[n];
    stack<int> seq;
    queue<char> result;
    for(int i = 0; i < n; i++){
        int tmp;
        cin >> tmp;
        arr[i] = tmp;
    }
    for(int i = 0; i < n; i++){
        while(cnt < arr[i]){
            seq.push(cnt + 1);
            cnt++;
            result.push('+');
        }
        if(seq.top() == arr[i]){
            seq.pop();
            result.push('-');
        }
        else{
            cout << "NO";
            return 0;
        }
    }
    while(!result.empty()){
        cout << result.front() << '\n';
        result.pop();
    }

    return 0;
}