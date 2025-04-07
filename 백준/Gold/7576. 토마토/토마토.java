import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, map[][], ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static Queue<Tomato> q = new LinkedList<>();

    static class Tomato {
        int r, c;

        Tomato(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        bfs(); // 토마토 익기 시작

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        ans = 0; // 초기화

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    q.offer(new Tomato(i, j));
            }
        }
    }

    static void bfs() {
        if (q.isEmpty()) { // 익을 토마토가 없는 경우
            ans = -1;
            return;
        }

        if (isFinish())  // 이미 다 익어있는 경우
            return;

        while (!q.isEmpty()) {
            int size = q.size();
            ans++;

            for (int s = 0; s < size; s++) {
                Tomato t = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr = t.r + dr[i];
                    int nc = t.c + dc[i];

                    if (check(nr, nc) && map[nr][nc] == 0) {
                        map[nr][nc] = 1;
                        q.offer(new Tomato(nr, nc));
                    }
                }
            }
        }

        if (!isFinish()) // 다 익지 못하는 경우
            ans = -1;
        else
            ans--;
    }

    static boolean isFinish() {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] == 0)
                    return false;
        return true;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}