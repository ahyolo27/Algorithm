import java.io.*;
import java.util.*;

public class Main {
    static int N, M, map[][], K;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static PriorityQueue<Corn> q = new PriorityQueue<>((o1, o2) -> o2.score - o1.score);
    static StringBuilder sb = new StringBuilder();

    static class Corn {
        int r, c, score;

        Corn(int r, int c, int score) {
            this.r = r;
            this.c = c;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setQueue(); // 가장자리 옥수수 담기
        getCorn(); // 옥수수 따기

        System.out.print(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());
    }

    static void setQueue() { // 가장자리의 옥수수를 미리 큐에 담아놓기
        int i = 1;
        for (int j = 1; j <= M; j++)
            q.offer(new Corn(i, j, map[i][j]));

        i = N;
        for (int j = 1; j <= M; j++)
            q.offer(new Corn(i, j, map[i][j]));

        int j = 1;
        for (i = 2; i < N; i++)
            q.offer(new Corn(i, j, map[i][j]));

        j = M;
        for (i = 2; i < N; i++)
            q.offer(new Corn(i, j, map[i][j]));
    }

    static void getCorn() { // 옥수수 따는 함수
        boolean visited[][] = new boolean[N + 1][M + 1];
        int cnt = 0; // 옥수수 개수 카운트

        while (!q.isEmpty()) {
            Corn corn = q.poll();
            if (visited[corn.r][corn.c]) continue; // 이미 방문한 경우 continue

            visited[corn.r][corn.c] = true; // 뽑고 나서 방문 처리

            for (int i = 0; i < 4; i++) {
                int nr = corn.r + dr[i];
                int nc = corn.c + dc[i];

                if (!isValid(nr, nc) || visited[nr][nc]) continue; // 유효하지 않거나 이미 방문한 경우 continue

                q.offer(new Corn(nr, nc, map[nr][nc])); // 유효한 인근 옥수수 넣기
            }

            cnt++; // 옥수수의 개수 증가
            sb.append(corn.r).append(" ").append(corn.c).append("\n");

            if (cnt == K) // 옥수수의 개수가 K개인 경우 return
                return;
        }
    }

    static boolean isValid(int r, int c) {
        return 1 <= r && r <= N && 1 <= c && c <= M;
    }
}