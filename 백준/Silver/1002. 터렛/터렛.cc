#include <iostream>
#include <cmath>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(NULL);

    int T;
    cin >> T;
    while(T--){
        int x1, y1, r1, x2, y2, r2;
        cin >> x1 >> y1 >> r1 >> x2 >> y2 >> r2;
        double distance = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
        if(distance == 0 && r1 == r2)
            cout << "-1\n";
        else if(distance < r1 + r2 && distance > abs(r2 - r1))
            cout << "2\n";
        else if(distance == r1 + r2 || distance == abs(r2 - r1))
            cout << "1\n";
        else
            cout << "0\n";
    }

    return 0;
}