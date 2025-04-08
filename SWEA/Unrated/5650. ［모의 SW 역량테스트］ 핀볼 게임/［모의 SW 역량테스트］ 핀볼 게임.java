import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, map[][], max;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static List<Wormhole> wormholes = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Wormhole {
        int idx, r, c;

        Wormhole(int idx, int r, int c) {
            this.idx = idx;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            startGame(); // 핀볼 게임 시작

            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());

        max = 0; // 초기화
        wormholes.clear();

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (6 <= map[i][j] && map[i][j] <= 10) // 웜홀이면
                    wormholes.add(new Wormhole(map[i][j], i, j));
            }
        }
    }

    static void startGame() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (map[i][j] != 0) continue; // 빈 공간이 아닌 경우 continue

                for (int d = 0; d < 4; d++) {
                    int score = simulate(i, j, d);
                    max = Math.max(max, score);
                }
            }
        }
    }

    static int simulate(int startR, int startC, int d) {
        int score = 0;

        int r = startR;
        int c = startC;

        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isValid(nr, nc)) { // 벽에 부딪힌 경우
                d = (d + 2) % 4; // 방향 반전
                score++;
            }

            else if (map[nr][nc] == -1 || (nr == startR && nc == startC)) // 블랙홀을 만나거나 시작점으로 돌아온 경우
                return score;

            else if (1 <= map[nr][nc] && map[nr][nc] <= 5) { // 블록을 만난 경우
                d = getBlock(map[nr][nc], d); // 방향 전환
                score++;
            }

            else if (6 <= map[nr][nc] && map[nr][nc] <= 10) { // 웜홀을 만난 경우
                int[] pos = getWormhole(nr, nc); // 현재 위치를 반대쪽 웜홀 위치로 수정
                nr = pos[0];
                nc = pos[1];
            }

            r = nr; // 좌표 갱신
            c = nc;
        }
    }

    static int getBlock(int map, int dir) {
        switch (map) {
            case 1:
                switch (dir) {
                    case 2:
                        dir = 1;
                        break;
                    case 3:
                        dir = 0;
                        break;
                    default:
                        dir = (dir + 2) % 4;
                        break;
                }
                break;
            case 2:
                switch (dir) {
                    case 0:
                        dir = 1;
                        break;
                    case 3:
                        dir = 2;
                        break;
                    default:
                        dir = (dir + 2) % 4;
                        break;
                }
                break;
            case 3:
                switch (dir) {
                    case 0:
                        dir = 3;
                        break;
                    case 1:
                        dir = 2;
                        break;
                    default:
                        dir = (dir + 2) % 4;
                        break;
                }
                break;
            case 4:
                switch (dir) {
                    case 1:
                        dir = 0;
                        break;
                    case 2:
                        dir = 3;
                        break;
                    default:
                        dir = (dir + 2) % 4;
                        break;
                }
                break;
            case 5:
                dir = (dir + 2) % 4;
                break;
        }

        return dir;
    }

    static int[] getWormhole(int r, int c) {
        int pos[] = new int[2];
        int idx = map[r][c]; // 웜홀 번호

        for (Wormhole w : wormholes) {
            if (w.idx == idx && !(w.r == r && w.c == c)) { // 반대쪽 웜홀 찾기
                pos[0] = w.r;
                pos[1] = w.c;
                return pos; // 재설정할 좌표 반환
            }
        }
        return null;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}