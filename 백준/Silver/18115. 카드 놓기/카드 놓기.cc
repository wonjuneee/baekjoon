#include <iostream>
#include <deque>
using namespace std;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n;
    cin >> n;
    int skill[n];
    for(int i = 0; i < n; i++){
        cin >> skill[i];
    }
    deque<int> cards;
    int number = 1;
    for(int i = n - 1; i >= 0; i--, number++){
        switch(skill[i]){
            case 1 :{
                cards.push_back(number);
                break;
            }
            case 2 :{
                int tmp = cards.back();
                cards.pop_back();
                cards.push_back(number);
                cards.push_back(tmp);
                break;
            }
            case 3 :{
                cards.push_front(number);
                break;
            }
            default :break;
        }
    }
    for(int i = n - 1; i >= 0; i--)
        cout << cards[i] << ' ';

    return 0;   
}