import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, r, c, map[][], dice[];
    static int dr[] = {1, 0, 0, -1}, dc[] = {0, 1, -1, 0};
    static Queue<Integer> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        input(); // 입력

        simulate(); // 주사위 굴리기

        System.out.println(sb); // 결과 출력
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++)
            q.add(Integer.parseInt(st.nextToken()));
    }

    static void simulate() {

        while (!q.isEmpty()) {
            int command = q.poll();

            int dir = command % 4;

            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (nr < 0 || N <= nr || nc < 0 || M <= nc) continue; // 명령 수행 X

            // 칸 및 좌표 갱신
            if (map[r][c] == 0)
                map[r][c] = dice[5];
            else {
                dice[5] = map[r][c];
                map[r][c] = 0;
            }
            r = nr;
            c = nc;

            // 주사위 갱신
            int tmp = dice[0];
            switch (dir) {
                case 0: // 남
                    dice[0] = dice[1];
                    dice[1] = dice[5];
                    dice[5] = dice[4];
                    dice[4] = tmp;
                    break;
                case 1: // 동
                    dice[0] = dice[3];
                    dice[3] = dice[5];
                    dice[5] = dice[2];
                    dice[2] = tmp;
                    break;
                case 2: // 서
                    dice[0] = dice[2];
                    dice[2] = dice[5];
                    dice[5] = dice[3];
                    dice[3] = tmp;
                    break;
                case 3: // 북
                    dice[0] = dice[4];
                    dice[4] = dice[5];
                    dice[5] = dice[1];
                    dice[1] = tmp;
                    break;
            }

            sb.append(dice[0]).append("\n");
        }
    }
}