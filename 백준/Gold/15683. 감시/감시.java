import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][], ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static List<Camera> cctv = new ArrayList<>();

    static class Camera {
        int r, c, idx;

        Camera(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        if (!cctv.isEmpty()) {
            prepare(); // 5번 cctv 먼저 배치
            comb(0); // 나머지 1~4번 cctv 배치
        }

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) // ans 초기값: 초기 빈칸(0)의 개수
                    ans++;
                else if (map[i][j] < 6) // cctv인 경우 list에 추가
                    cctv.add(new Camera(i, j, map[i][j]));
            }
        }
    }

    static void prepare() {
        cctv.sort((o1, o2) -> o2.idx - o1.idx);

        while (!cctv.isEmpty()) { // 5번 카메라만 모두 설치
            Camera camera = cctv.get(0);
            if (camera.idx != 5) break;
            for (int i = 0; i < 4; i++) {
                int nr = camera.r + dr[i];
                int nc = camera.c + dc[i];

                while (isValid(nr, nc) && map[nr][nc] != 6) { // 유효한 빈칸이면 map에 카메라 표시
                    if (map[nr][nc] == 0)
                        map[nr][nc] = camera.idx;
                    nr += dr[i];
                    nc += dc[i];
                }
            }
            cctv.remove(0); // 설치 완료한 5번 카메라는 삭제
        }
    }

    static void comb(int depth) { // 1~4번 카메라는 백트래킹으로 배치
        if (depth == cctv.size()) {
            ans = Math.min(calcZero(), ans);
            return;
        }

        Camera camera = cctv.get(depth);

        for (int dir = 0; dir < 4; dir++) {
            if (camera.idx == 2 && dir > 1) continue; // 무의미한 경우
            List<int[]> changed = setCctv(camera, dir);
            comb(depth + 1);
            rollback(changed);
        }
    }

    static List<int[]> setCctv(Camera camera, int d) {
        List<int[]> changed = new ArrayList<>();

        if (camera.idx == 1) {
            int dir = (1 + d) % 4;

            int nr = camera.r + dr[dir];
            int nc = camera.c + dc[dir];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir];
                nc += dc[dir];
            }
        } else if (camera.idx == 2) {
            int dir1 = (1 + d) % 4;
            int dir2 = (3 + d) % 4;

            int nr = camera.r + dr[dir1];
            int nc = camera.c + dc[dir1];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir1];
                nc += dc[dir1];
            }

            nr = camera.r + dr[dir2];
            nc = camera.c + dc[dir2];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir2];
                nc += dc[dir2];
            }
        } else if (camera.idx == 3) {
            int dir1 = d % 4;
            int dir2 = (1 + d) % 4;

            int nr = camera.r + dr[dir1];
            int nc = camera.c + dc[dir1];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir1];
                nc += dc[dir1];
            }

            nr = camera.r + dr[dir2];
            nc = camera.c + dc[dir2];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir2];
                nc += dc[dir2];
            }
        } else { // camera.idx == 4
            int dir1 = (3 + d) % 4;
            int dir2 = d % 4;
            int dir3 = (1 + d) % 4;

            int nr = camera.r + dr[dir1];
            int nc = camera.c + dc[dir1];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir1];
                nc += dc[dir1];
            }

            nr = camera.r + dr[dir2];
            nc = camera.c + dc[dir2];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir2];
                nc += dc[dir2];
            }

            nr = camera.r + dr[dir3];
            nc = camera.c + dc[dir3];
            while (isValid(nr, nc) && map[nr][nc] != 6) {
                if (map[nr][nc] == 0) {
                    changed.add(new int[]{nr, nc});
                    map[nr][nc] = camera.idx;
                }
                nr += dr[dir3];
                nc += dc[dir3];
            }
        }

        return changed;
    }

    static void rollback(List<int[]> changed) {
        for (int[] pos : changed)
            map[pos[0]][pos[1]] = 0;
    }

    static int calcZero() {
        int cnt = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 0)
                    cnt++;
        return cnt;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}