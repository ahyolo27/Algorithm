import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static boolean map[][];
    static Queue<Dragon> q = new LinkedList<>();

    static class Dragon {
        int r, c, d, g;

        Dragon(int r, int c, int d, int g) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.g = g;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // input

        makeDragonCurve(); // 드래곤 커브 그리기
        calc(); // 정사각형 세기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            q.add(new Dragon(y, x, d, g));
        }
    }

    static void makeDragonCurve() {
        int dr[] = {0, -1, 0, 1}, dc[] = {1, 0, -1, 0};

        while (!q.isEmpty()) {
            Dragon dragon = q.poll();

            // init
            int age = 0;
            List<Integer> directions = new ArrayList<>();

            // 0 세대에서 이동 후 현재 좌표
            int r = dragon.r + dr[dragon.d];
            int c = dragon.c + dc[dragon.d];

            // 방문 좌표 표시
            map[dragon.r][dragon.c] = true;
            map[r][c] = true;

            // 방향 저장
            int dir = dragon.d;
            directions.add(dir);

            age++;

            ArrayList<Integer> tmpDir = new ArrayList<>();
            while (age <= dragon.g) {
                for (int i = directions.size() - 1; i >= 0; i--) {
                    int nowDir = (directions.get(i) + 1) % 4; // 현재 방향

                    int nr = r + dr[nowDir];
                    int nc = c + dc[nowDir];

                    map[nr][nc] = true; // 방문 체크
                    tmpDir.add(nowDir); // 방향 임시 저장

                    r = nr;
                    c = nc;
                }

                directions.addAll(tmpDir); // 방향 누적 저장
                tmpDir.clear();
                age++;
            }
        }
    }

    static void calc() {
        for (int i = 0; i < 100; i++)
            for (int j = 0; j < 100; j++)
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) // 사각형 조건 만족
                    ans++;
    }
}