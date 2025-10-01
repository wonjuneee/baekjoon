import java.io.*;
import java.util.*;

public class Main {

    static int n;

    static final int[] stairs = new int[301];
    static final long[] maxPoints = new long[301];
    static final int[] consecutiveness = new int[301];

    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    /**
     * DP를 통해 해결할 수 있는 문제로, 조건에 따라 이전 값들을 어떻게 참조할 지 분기처리를 진행할 수 있다.
     * 최대 점수를 얻은 경우의 수에 대해 연속으로 계단을 오른 횟수를 기록하여,
     * 해당 기록을 기반으로 다음 계단에서 어떤 값을 참조할 지 결정한다.
     * 1. 직전 칸: 두 계단 연속
     * - 두 칸 전의 최대값 + 현재 칸 점수
     * - 세 칸 전의 최대값 + 직전 칸 점수 + 현재 칸 점수
     * 2. 직전 칸: 한 계단
     * - 두 칸 전의 최대값 + 현재 칸 점수
     * - 직전 칸의 최대값 + 현재 칸 점수
     */
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        for (int stair = 1; stair <= n; stair++) {
            stairs[stair] = Integer.parseInt(br.readLine());
        }
        maxPoints[1] = stairs[1];
        maxPoints[2] = stairs[1] + stairs[2];
        consecutiveness[1] = 1;
        consecutiveness[2] = 2;

        for (int i = 3; i <= n; i++) {
            if (consecutiveness[i - 1] == 2) {
                if (maxPoints[i - 2] + stairs[i] >= maxPoints[i - 3] + stairs[i - 1] + stairs[i]) {
                    maxPoints[i] = maxPoints[i - 2] + stairs[i];
                    consecutiveness[i] = 1;
                } else {
                    maxPoints[i] = maxPoints[i - 3] + stairs[i - 1] + stairs[i];
                    consecutiveness[i] = 2;
                }
            } else {
                if (maxPoints[i - 2] + stairs[i] >= maxPoints[i - 1] + stairs[i]) {
                    maxPoints[i] = maxPoints[i - 2] + stairs[i];
                    consecutiveness[i] = 1;
                } else {
                    maxPoints[i] = maxPoints[i - 1] + stairs[i];
                    consecutiveness[i] = 2;
                }
            }
        }
        bw.write(String.valueOf(maxPoints[n]));
        bw.flush();
        bw.close();
    }
}