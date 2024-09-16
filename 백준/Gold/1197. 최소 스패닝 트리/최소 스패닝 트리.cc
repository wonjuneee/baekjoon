#include <iostream>
#include <queue>
using namespace std;

// 1197번 - 최소 스패닝 트리
// 간선이 비교적 많은 밀집 그래프(Dense Graph)이므로 Prim 알고리즘이 적합할 것이라고 판단함.

struct compare{
    bool operator()(pair<int, int> a, pair<int, int> b){
        return a.second > b.second;
    }
}; // 배열, 벡터에서의 compare과 t/f 로직이 반대로 적용

int v, e, a, b, c;
vector<pair<int, int> > graph[10001];
bool visited[10001];
priority_queue<pair<int, int>, vector<pair<int, int> >, compare> pq;

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> v >> e;
    for (int i = 0; i < e; i++){
        cin >> a >> b >> c;
        graph[a].push_back(make_pair(b, c));
        graph[b].push_back(make_pair(a, c));
    }
    pq.push(make_pair(1, 0));

    long long result = 0;
    int cnt = 0, node = 1;
    while(!pq.empty()){
        int node = pq.top().first;
        int weight = pq.top().second;
        pq.pop();

        if(visited[node])
            continue;
        result += weight;
        visited[node] = true;

        for (int i = 0; i < graph[node].size(); i++){
            int nextNode = graph[node][i].first;
            int nextWeight = graph[node][i].second;
            pq.push(make_pair(nextNode, nextWeight));
        }
    }
    cout << result;

    return 0;
}