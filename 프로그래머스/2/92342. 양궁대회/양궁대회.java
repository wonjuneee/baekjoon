import java.io.*;
import java.util.*;

class Solution {
    
    static int[] maxArrows = new int[11];
    static int maxScoreDiff = 0;
    
    static final int[] neededArrows = new int[11];
    
    /**
     * 10점부터 0점까지 라이언이 점수를 획득한 경우와 미획득한 경우를 dfs 및 백트래킹 top-down 방식으로 순회한다.
     * - 10점을 root 노드로하며 획득/미획득으로 자식노드가 나뉘는 이진트리를 그리면 문제 이해에 도움이 될 것 같다.
     * 이때 낮은 점수에 쏜 화살이 많은 경우를 answer로 출력해야 하므로, count가 남을 경우 0점에 모두 쏴버리면 된다.
     */
    public int[] solution(int n, int[] info) {
        int[] answer;
        int apeachScore = 0, ryanScore = 0;

        // 어피치 점수를 계산하며 라이언이 해당 점수를 얻기 위해 쏴야하는 화살 수를 저장한다. 
        // - 어피치가 쏜 화살 개수 + 1
        // - 0점의 경우 화살 개수가 의미를 가지지 않으므로 항상 0으로 고정
        for (int i = 0; i <= 9; i++) {
            if (info[i] > 0) apeachScore += (10 - i);
            neededArrows[i] = info[i] + 1;
        }

        int[] tmpArrows = new int[11];
        Arrays.fill(tmpArrows, 0);
        dfs(0, apeachScore, ryanScore, n, tmpArrows);
        
        answer = Arrays.stream(maxArrows).allMatch(arrow -> arrow == 0) ? new int[]{ -1 } : Arrays.copyOf(maxArrows, 11);
        return answer;
    }
    
    
    // 각 점수 칸에 대해 어피치, 라이언 각각의 점수와 라이언이 쏠 수 있는 남은 화살 수, 라이언이 각 점수에 쏜 화살 개수를 나타내는 배열을 전파한다.
    void dfs(int idx, int apeach, int ryan, int count, int[] arrows) {
        // 엣지케이스1: 어피치 점수 == 라이언 점수인 경우를 예외처리 해야한다.
        if (count == 0 && apeach < ryan && maxScoreDiff <= ryan - apeach) {
            
            // 엣지케이스2.1: maxScoreDiff가 새로 갱신될 경우, maxArrows도 무조건 갱신 해야한다.
            if (maxScoreDiff < ryan - apeach) {
                maxScoreDiff = ryan - apeach;
                maxArrows = Arrays.copyOf(arrows, 11);
            } else {
                for (int i = 10; i >= 0; i--) {
                    if (maxArrows[i] < arrows[i]) {
                        maxArrows = Arrays.copyOf(arrows, 11);
                        return;
                    } 
                    // 엣지케이스2.2: 동일한 점수차이를 가지는 arrows 배열에서, 낮은 점수에서의 화살 개수가 전역 배열(maxArrows)에서 더 큰 경우 다음 점수를 확인할 필요없이 바로 return 해야한다.
                    else if (maxArrows[i] > arrows[i]) {
                        return;
                    }
                }
            }
        } else if (count == 0 && (apeach >= ryan || maxScoreDiff > ryan - apeach)) {
            return;
        }
        
        if (idx == 11) {
            return;
        }
        
        // idx == 10, 즉 0점에 남은 화살(count)을 맞혀야 maxArrows 배열 갱신이 가능하다.
        if (neededArrows[idx] <= count) {
            int tmp = arrows[idx];
            int usedArrows = idx == 10 ? count : neededArrows[idx];
            arrows[idx] = usedArrows;
            
            dfs(idx + 1, usedArrows == 1 ? apeach : apeach - (10 - idx), ryan + (10 - idx), count - usedArrows, arrows);
            arrows[idx] = tmp;
        }
        dfs(idx + 1, apeach, ryan, count, arrows);
    } 
}