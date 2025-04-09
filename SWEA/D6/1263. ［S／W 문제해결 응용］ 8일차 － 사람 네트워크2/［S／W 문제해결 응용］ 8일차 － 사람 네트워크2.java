import java.util.*;
import java.io.*;

public class Solution {
    static final int INF = 1000000;
    static int T, N, weight[][], ans;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            floyd(); // 플로이드 워셜 알고리즘
            getAnswer(); // 정답 찾기

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE; // 초기화

        weight = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
                if (weight[i][j] == 0) weight[i][j] = INF;
            }
            weight[i][i] = 0;
        }
    }

    static void floyd() {
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
    }

    static void getAnswer() {
        for (int i = 1; i <= N; i++)
            ans = Math.min(ans, CC(i));
    }

    static int CC(int n) {
        int cc = 0;
        for (int j = 1; j <= N; j++)
            cc += weight[n][j];
        return cc;
    }
}