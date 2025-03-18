import java.io.*;
import java.util.*;

public class Main {
    static int N, map[][], cnt;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static ArrayList<Integer> home = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        start(); // 탐색 시작
        output(); // 출력
        
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++)
                map[i][j] = str.charAt(j) - '0';
        }
    }

    static void start() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c] && map[r][c] == 1) {
                    cnt = 0; // 단지 내 집 count 변수 초기화
                    dfs(r, c);
                    home.add(cnt); // 탐색 후 정답 배열에 count 추가
                }
            }
        }
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        cnt++;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) // 조건을 만족하면 탐색
                dfs(nr, nc);
        }
    }

    static boolean check(int r, int c) { // 범위 내에 있으면 true
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static void output() {
        Collections.sort(home); // 오름차순 정렬

        sb.append(home.size()).append('\n');
        for (int h : home)
            sb.append(h).append('\n');
    }
}