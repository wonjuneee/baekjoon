#include <iostream>
#include <cmath>
#include <deque>
using namespace std;

int main()
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

    int N;
    deque<int> dq;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		dq.push_back(i);
	}

	for (int i = 1; i <= N - 1; i++) {
		long long p = pow(i, 3);
		p--;
		p %= (N - i + 1);

		while (p--) {
			dq.push_back(dq.front());
			dq.pop_front();
		}
		dq.pop_front();
	}
	cout << dq.front() << "\n";
}