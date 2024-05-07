#include <bits/stdc++.h>
using namespace std;

//1541번

int main(){
    string expr;
    cin >> expr;
    int numbers[25];
    char operation[25];

    int num_idx = 0, oper_idx = 1, cnt = 0;
    for (int i = 0; i < expr.size(); i++){
        if (expr[i] == '+' || expr[i] == '-'){
            operation[oper_idx] = expr[i];
            oper_idx++;
            numbers[num_idx] = stoi(expr.substr(i - cnt, i));
            cnt = 0;
            num_idx++;
        }
        else if(expr[i] != '+' && expr[i] != '-' && i == expr.size() - 1){
            numbers[num_idx] = stoi(expr.substr(i - (cnt), i + 1));
        }
        else{
            cnt++;
        }
    }
    int result = numbers[0], neg = 0;
    bool isNeg = false;
    for (int i = 1; i < oper_idx; i++){
        if (operation[i] == '+' && !isNeg){
            result += numbers[i];
        }
        else if (operation[i] == '+' && isNeg){
            result -= numbers[i];
        }
        else if (operation[i] == '-') {
            isNeg = true;
            result -= numbers[i];
        }
    }

    cout << result;
    return 0;
}