#include <bits/stdc++.h>
using namespace std;

class setOperator{
    public:
        vector<int> &count;
        int size;
        int method = 0;

        setOperator(vector<int> &count) : size(20), count(count){};
        setOperator(int size, vector<int> &count) : size(size), count(count){};

        void setMethod(int m){
            method = m;
        }

        void runMethod(int x, int m){
            setMethod(m);
            switch (method){
                case 0:
                    add(x);
                    break;
                case 1:
                    remove(x);
                    break;
                case 2:
                    check(x);
                    break;
                case 3:
                    toggle(x);
                    break;
                case 4:
                    all();
                    break;
                case 5:
                    empty();
                    break;
                default:
                    break;
            }
        };

        bool isExist(int x){
            return (count[x - 1] == 1);
        }

        void add(int x){
            count[x - 1] = 1;
        };

        void remove(int x){
            count[x - 1] = 0;
        };

        void check(int x){
            if(isExist(x))
                cout << 1 << '\n';
            else
                cout << 0 << '\n';
        };

        void toggle(int x){
            if(isExist(x))
                remove(x);
            else
                add(x);
        };

        void all(){
            for (int i = 1; i <= size; i++){
                add(i);
            }
        };

        void empty(){
            for (int i = 1; i <= size; i++){
                remove(i);
            }
        };
};

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int N;
    cin >> N;
    vector<string> operations = {"add", "remove", "check", "toggle", "all", "empty"};
    vector<int> count(20, 0);
    setOperator oper(count);
    for (int i = 0; i < N; i++){
        string operation;
        int number = 0;
        cin >> operation;
        if(operation != "all" && operation != "empty"){
            cin >> number;
        }
        for (int j = 0; j < 6; j++){
            if(operations[j] == operation){
                oper.runMethod(number, j);
                break;
            }
        }
    }

    return 0;   
}