import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, W, H, map[][], ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            chooseLine(0, new ArrayList<>()); // 시뮬레이션 시작

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        ans = H * W; // 초기화
    }

    static void chooseLine(int depth, List<Integer> list) {
        if (depth == N) {
            int copy[][] = copyMap();
            simulate(copy, list); // 벽돌 깨기
            return;
        }

        for (int col = 0; col < W; col++) {
            list.add(col);
            chooseLine(depth + 1, list);
            list.remove(list.size() - 1);
        }
    }

    static void simulate(int map[][], List<Integer> list) {

        for (int start : list) {
            int r = 0; // 공의 초기 좌표 설정
            int c = start;

            while (true) {
                if (r == H - 1 && map[r][c] == 0) // 끝까지 비어있는 줄인 경우
                    break;

                if (map[r][c] > 0) { // 깨야 하는 벽돌을 만나면 시작
                    crashBricks(r, c, map); // 벽돌 깨기
                    dropBricks(map); // 빈공간 정리
                    break;
                }

                r++; // 내려가기
            }
        }

        ans = Math.min(countRemains(map), ans);
    }

    static void crashBricks(int r, int c, int map[][]) {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c));

        while (!q.isEmpty()) {
            Pos p = q.poll();
            int dist = map[p.r][p.c] - 1;
            map[p.r][p.c] = 0;

            for (int i = 0; i < 4; i++) {
                int d = 1;
                while (d <= dist) {
                    int nr = p.r + d * dr[i];
                    int nc = p.c + d * dc[i];

                    if (!isValid(nr, nc)) break; // 공이 범위 밖으로 벗어나면 종료

                    if (map[nr][nc] == 1) // 바로 부술 수 있는 벽돌인 경우
                        map[nr][nc] = 0; // 벽돌 부수기
                    else if (map[nr][nc] > 1) // 연쇄 폭발이 일어나는 벽돌인 경우
                        q.offer(new Pos(nr, nc)); // 큐에 추가

                    d++;
                }
            }
        }
    }

    static void dropBricks(int map[][]) { // 벽돌들을 아래로 정렬
        Stack<Integer> s = new Stack<>();

        for (int j = 0; j < W; j++) {
            s.clear();
            for (int i = 0; i < H; i++) {
                if (map[i][j] > 0) {
                    s.add(map[i][j]);
                    map[i][j] = 0;
                }
            }
            int i = H - 1;
            while (!s.isEmpty())
                map[i--][j] = s.pop();
        }
    }

    static int countRemains(int map[][]) { // 남은 벽돌의 개수 return
        int cnt = 0;

        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++)
                if (map[i][j] > 0) cnt++;

        return cnt;
    }

    static int[][] copyMap() { // 시뮬레이션에 사용할 map 복사
        int copy[][] = new int[H][W];
        for (int i = 0; i < H; i++)
            copy[i] = map[i].clone();
        return copy;
    }

    static boolean isValid(int r, int c) { // 범위 내 좌표이면 true
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}