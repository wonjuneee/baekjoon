#include <iostream>
#include <vector>
#include <string>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N;
    string re;
    vector<string> file;
    cin >> N;
    cin.ignore();
    getline(cin, re);
    for(int i = 0; i < N; i++){
        string tmp;
        getline(cin, tmp);
        file.push_back(tmp);
    }
    for(int i = 0; i < N; i++){
        int reIdx = 0;
        bool valid = false;
        for(int j = 0; j < file[i].length(); j++){
            if(file[i][j] == re[reIdx]){
                reIdx++;
            }
            else if(re[reIdx] == '*' && re[reIdx + 1] == file[i][j]){
                int tmp = reIdx + 1, jTmp = j;
                while(re[tmp] == file[i][jTmp]){
                    tmp++;
                    jTmp++;
                    if(tmp == re.size() + 1 && jTmp == file[i].size() + 1){
                        cout << "DA\n";
                        valid = true;
                    }
                }
                if(valid)
                    break;
            }
            else if(re[reIdx] == '*')
                continue;
            else{
                break;
            }
        }
        if(!valid)
            cout << "NE\n";
    }

    return 0;   
}