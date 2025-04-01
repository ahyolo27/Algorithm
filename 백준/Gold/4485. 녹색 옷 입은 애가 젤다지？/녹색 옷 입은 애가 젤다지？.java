import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, map[][], rupees[][], ans;
    static boolean visited[][];
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int r, c, w;

        Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int t = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            input(); // 입력
            dijkstra(); // 다익스트라 시작
            sb.append("Problem ").append(t++).append(": ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        ans = 0;
        visited = new boolean[N][N];

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        rupees = new int[N][N];
        for (int i = 0; i < N; i++)
            Arrays.fill(rupees[i], INF);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        pq.add(new Node(0, 0, map[0][0]));
        rupees[0][0] = map[0][0];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            visited[node.r][node.c] = true;

            if (node.r == N - 1 && node.c == N - 1) { // 도착하면 종료
                ans = rupees[node.r][node.c];
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = node.r + dr[i];
                int nc = node.c + dc[i];

                if (check(nr, nc) && !visited[nr][nc]) {
                    if (rupees[nr][nc] > node.w + map[nr][nc]) {
                        rupees[nr][nc] = node.w + map[nr][nc];
                        pq.offer(new Node(nr, nc, rupees[nr][nc]));
                    }
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}