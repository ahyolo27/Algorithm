import java.io.*;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int M, N, K, map[][], cnt;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find();

        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int nx = Integer.parseInt(st.nextToken());
            int ny = Integer.parseInt(st.nextToken());

            for (int r = y; r < ny; r++)
                for (int c = x; c < nx; c++)
                    map[r][c] = 1; // 영역 표시
        }
    }

    static void find() {
        for (int r = 0; r < M; r++)
            for (int c = 0; c < N; c++) {
                if (!visited[r][c] && map[r][c] == 0) {
                    cnt = 1;
                    dfs(r, c);
                    list.add(cnt);
                }
            }
        Collections.sort(list); // 오름차순 정렬
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) {
                cnt++;
                dfs(nr, nc);
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < M && 0 <= c && c < N;
    }
}