import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, H, W, cnt;
    static char map[][];
    static boolean visited[][], keys[];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

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
            input();

            bfs();

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H + 2][W + 2];
        for (char[] lines : map)
            Arrays.fill(lines, '.');

        for (int i = 1; i <= H; i++) {
            String str = br.readLine();
            for (int j = 1; j <= W; j++)
                map[i][j] = str.charAt(j - 1);
        }

        visited = new boolean[H + 2][W + 2];
        keys = new boolean[26];

        String key = br.readLine();
        if (!key.equals("0"))
            for (int i = 0; i < key.length(); i++)
                keys[key.charAt(i) - 'a'] = true;

        cnt = 0; // 초기화
    }

    static void bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        visited[0][0] = true;

        Queue<Pos> doors[] = new Queue[26]; // 열 수 있는 문의 위치 모음
        for (int i = 0; i < 26; i++)
            doors[i] = new LinkedList<>();

        while (!q.isEmpty()) {
            Pos p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if (!isValid(nr, nc) || map[nr][nc] == '*' || visited[nr][nc]) continue; // 갈 수 없는 경우/이미 간 경우

                if (map[nr][nc] == '$') { // 문서
                    cnt++; // 문서 먹음
                    map[nr][nc] = '.';
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                } else if ('a' <= map[nr][nc] && map[nr][nc] <= 'z') { // 열쇠
                    keys[map[nr][nc] - 'a'] = true; // 키 먹음
                    while (!doors[map[nr][nc] - 'a'].isEmpty()) { // 방금 얻은 열쇠로 열 수 있는 문 모두 큐에 넣기
                        Pos door = doors[map[nr][nc] - 'a'].poll();
                        if (!visited[door.r][door.c]) {
                            visited[door.r][door.c] = true;
                            q.add(door);
                        }
                    }
                    map[nr][nc] = '.';
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                } else if ('A' <= map[nr][nc] && map[nr][nc] <= 'Z') { // 문
                    if (!keys[map[nr][nc] - 'A']) // 열 수 없으면
                        doors[map[nr][nc] - 'A'].add(new Pos(nr, nc));
                    else { // 열 수 있으면
                        map[nr][nc] = '.';
                        visited[nr][nc] = true;
                        q.add(new Pos(nr, nc));
                    }
                } else { // 빈 칸
                    visited[nr][nc] = true;
                    q.add(new Pos(nr, nc));
                }
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < H + 2 && 0 <= c && c < W + 2;
    }
}