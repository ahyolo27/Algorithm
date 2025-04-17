import java.io.*;
import java.util.*;

public class Main {
    static int max;

    static Fish map[][];
    static int dr[] = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dc[] = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    static class Fish {
        int w, dir;

        Fish(int w, int dir) {
            this.w = w;
            this.dir = dir;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        simulate(); // 청소년 상어 밥 먹이기

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        max = 0; // 초기화
        map = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int w = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[i][j] = new Fish(w, dir);
            }
        }
    }

    static void simulate() {
        int sum = map[0][0].w;

        Fish originMap[][] = copyMap(map);
        originMap[0][0].w = 0; // 상어의 초기 위치

        moveShark(0, 0, sum, originMap);
    }

    static void moveShark(int r, int c, int sum, Fish originMap[][]) {
        int d = originMap[r][c].dir; // 현재 이동 방향
        originMap[r][c].w = 0; // 현재 위치 설정

        Fish map[][] = moveFish(copyMap(originMap)); // 물고기 먼저 이동
        map[r][c] = null;

        int level = 1; // 이동 정도
        int cnt = 0; // 이동 횟수

        while (true) {
            int nr = r + level * dr[d];
            int nc = c + level * dc[d];

            if (!isValid(nr, nc)) break; // 갈 수 없는 경우

            if (map[nr][nc] != null && map[nr][nc].w > 0) { // 먹을 수 있는 경우
                Fish[][] nextMap = copyMap(map);
                int tmp = nextMap[nr][nc].w;
                nextMap[nr][nc].w = 0;
                moveShark(nr, nc, sum + tmp, nextMap);
                cnt++;
            }
            level++;
        }

        if (cnt == 0)  // 더이상 이동이 불가능 한 경우 -> 갱신 후 종료
            max = Math.max(sum, max);
    }

    static Fish[][] moveFish(Fish map[][]) {
        int n = 1; // 물고기 번호

        while (n <= 16) {
            boolean isFound = false;
            for (int i = 0; i < 4 && !isFound; i++) {
                for (int j = 0; j < 4 && !isFound; j++) {
                    if (map[i][j] != null && map[i][j].w == n) {
                        isFound = true;

                        int d = map[i][j].dir;
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        int cnt = 0;

                        while (cnt < 8 && (!isValid(nr, nc) || (map[nr][nc] != null && map[nr][nc].w == 0))) { // 맵 밖이거나 상어인 경우 -> 방향 전환
                            d = d % 8 + 1;
                            nr = i + dr[d];
                            nc = j + dc[d];
                            cnt++;
                        }
                        if (cnt < 8 && isValid(nr, nc)) {
                            Fish tmp = map[nr][nc];
                            map[nr][nc] = map[i][j];
                            map[i][j] = tmp;
                            map[nr][nc].dir = d;
                        }
                    }
                }
            }
            n++;
        }
        return map;
    }

    static Fish[][] copyMap(Fish map[][]) { // map 깊은 복사
        Fish copy[][] = new Fish[4][4];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                Fish f = map[i][j];
                if (f != null)
                    copy[i][j] = new Fish(f.w, f.dir);
            }
        return copy;
    }

    static boolean isValid(int r, int c) { // 범위 내에 있으면 true
        return 0 <= r && r < 4 && 0 <= c && c < 4;
    }
}