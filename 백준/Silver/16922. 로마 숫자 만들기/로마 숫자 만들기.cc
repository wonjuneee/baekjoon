#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
 
using namespace std;
 
#define I 1
#define V 5
#define X 10
#define L 50
 
int N;
int answer = 0;
int sum = 0;
bool visited[1001] = {0};
char cArr[4] = {'I','V','X','L'};
int iArr[4] = {1,5,10,50};
 
vector<int> sumVec;
vector<string> strVec;
string str = "";
void pickDFS(int toPick, int start, int s){
    if(toPick==0){
        if(visited[s]==false){
            answer++;
            visited[s]=true;
        }
        return;
    }
    
    for(int i = start; i<4; i++){				//시작점을 바꿔서 중복 조합 구현 가능
        pickDFS(toPick-1, i, s+iArr[i]);
    }
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    cin>>N;
    pickDFS(N, 0, 0);
    cout << answer;
    
    return 0;
}