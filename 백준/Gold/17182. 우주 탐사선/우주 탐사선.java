import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K, T[][], ans;

    public static void main(String[] args) throws IOException {
        input(); // 입력

        floyd(); // 비용 전처리
        dfs(K, 0, 1 << K); // 최단 경로 탐색

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        T = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                T[i][j] = Integer.parseInt(st.nextToken());
        }
        ans = Integer.MAX_VALUE;
    }

    static void dfs(int now, int time, int visited) {
        if (visited == (1 << N) - 1) {
            ans = Math.min(ans, time);
            return;
        }

        if (time >= ans) return; // 가지치기

        for (int next = 0; next < N; next++) {
            if ((visited & (1 << next)) != 0) continue;

            dfs(next, time + T[now][next], visited | (1 << next));
        }
    }

    static void floyd() {
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++)
                    T[i][j] = Math.min(T[i][j], T[i][k] + T[k][j]);
    }
}