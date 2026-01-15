import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, total, map[][], ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static List<Pos> candidates = new ArrayList<>();

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력

        comb(0, 0, new boolean[candidates.size()]);

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        total = 0;
        ans = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 1) {
                    if (map[i][j] == 2) { // 바이러스 놓을 수 있는 칸
                        candidates.add(new Pos(i, j));
                        map[i][j] = 0; // -> 빈칸
                    }

                    total++; // 빈칸의 개수
                }
            }
        }
    }

    static void comb(int idx, int cnt, boolean pos[]) { // 바이러스 놓는 위치의 조합
        if (cnt == M) {
            simulate(pos);
            return;
        }

        for (int i = idx; i < candidates.size(); i++) {
            pos[i] = true;
            comb(i + 1, cnt + 1, pos);
            pos[i] = false;
        }
    }

    static void simulate(boolean pos[]) {
        Queue<Pos> q = new LinkedList<>();
        boolean visited[][] = new boolean[N][N];

        int cnt = 0; // 바이러스 개수
        int time = 0; // 경과 시간

        // 시작점 세팅
        for (int i = 0; i < pos.length; i++) {
            if (pos[i]) {
                q.add(candidates.get(i));
                visited[candidates.get(i).r][candidates.get(i).c] = true;
                cnt++;
            }
        }

        // 시뮬레이션
        if (cnt == total) { 
            ans = 0; // 시뮬레이션 돌리기 전에 이미 조건을 충족
            return;
        }
        while (!q.isEmpty()) {
            int size = q.size();
            while (size > 0) {
                Pos p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nr = p.r + dr[i];
                    int nc = p.c + dc[i];

                    if (!isValid(nr, nc) || visited[nr][nc] | map[nr][nc] == 1) continue; // 접근 불가능

                    if (!visited[nr][nc] && map[nr][nc] == 0) {
                        visited[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                        cnt++;
                    }
                }
                size--;
            }

            time++;
            if (cnt == total)
                ans = Math.min(time, ans);
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}