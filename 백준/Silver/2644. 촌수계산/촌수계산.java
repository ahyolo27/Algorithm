import java.io.*;
import java.util.*;

public class Main {
    static int N, A, B, map[][], ans;
    static boolean visited[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find(A, 0); // 촌수 찾기

        if (ans > 0)
            System.out.println(ans);
        else
            System.out.println(-1);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = map[y][x] = 1;
        }
    }

    static void find(int start, int cnt) {
        if (start == B) { // 도착한 경우
            ans = cnt;
            return;
        }

        visited[start] = true;
        for (int i = 1; i <= N; i++)
            if (map[start][i] == 1 && !visited[i]) {
                find(i, cnt + 1);
            }
    }
}