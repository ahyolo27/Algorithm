import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, K, map[][];
    static int max, ans;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            input(); // 입력

            List<Pos> list = new ArrayList<>(); // 가장 높은 봉우리를 담을 리스트
            for (int r = 0; r < N; r++)
                for (int c = 0; c < N; c++)
                    if (max == map[r][c])
                        list.add(new Pos(r, c));

            for (int k = 0; k <= K; k++) { // 봉우리 깎기
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < N; c++) {

                        if (map[r][c] - k < 0)
                            continue;
                        else
                            map[r][c] -= k; // 깎고 탐색 진행

                        for (int i = 0; i < list.size(); i++)
                            makeRoad(list.get(i).r, list.get(i).c, map);

                        map[r][c] += k; // 원상 복구
                    }
                }
            }
            System.out.println("#" + (t + 1) + " " + ans);
        }
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 값 초기화
        map = new int[N][N];
        max = 0;
        ans = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }
    }

    static void makeRoad(int r, int c, int copy[][]) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c)); // 초기값 설정
        int cnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            cnt++;
            for (int i = 0; i < size; i++) {
                Pos p = q.poll();
                for (int t = 0; t < 4; t++) {
                    int nr = p.r + dr[t];
                    int nc = p.c + dc[t];
                    if (check(nr, nc) && copy[p.r][p.c] > copy[nr][nc])
                        q.offer(new Pos(nr, nc));
                }
            }
        }
        ans = Math.max(ans, cnt);
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}