import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, map[][];

    public static void main(String[] args) throws IOException {

        input();

        calc();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void calc() {
        int sum[][] = new int[N][M];
        sum[0][0] = map[0][0];
        for (int j = 1; j < M; j++) // 왼쪽으로
            sum[0][j] = sum[0][j - 1] + map[0][j];

        int left[] = new int[M];
        int right[] = new int[M];
        for (int i = 1; i < N; i++) {
            // 아래로
            for (int j = 0; j < M; j++)
                sum[i][j] = sum[i - 1][j] + map[i][j];

            // init
            Arrays.fill(left, 0);
            Arrays.fill(right, 0);

            // 왼쪽으로
            left[0] = sum[i][0];
            for (int j = 1; j < M; j++)
                left[j] = Math.max(left[j - 1] + map[i][j], sum[i][j]);

            // 오른쪽으로
            right[M - 1] = sum[i][M - 1];
            for (int j = M - 2; j >= 0; j--)
                right[j] = Math.max(right[j + 1] + map[i][j], sum[i][j]);

            // 합산
            for (int j = 0; j < M; j++)
                sum[i][j] = Math.max(left[j], right[j]);
        }

        System.out.println(sum[N - 1][M - 1]);
    }
}