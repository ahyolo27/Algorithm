import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, K, map[][], ans;
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};
    static boolean visited[][];
    static PriorityQueue<Cell> pq = new PriorityQueue<>(((o1, o2) -> o2.w - o1.w));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Cell {
        int r, c, w, hp;

        Cell(int r, int c, int w, int hp) {
            this.r = r;
            this.c = c;
            this.w = w;
            this.hp = hp;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            start(); // 줄기세포 배양 시작

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = 0; // 초기화
        pq.clear();
        visited = new boolean[700][700];
        map = new int[700][700];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int cell = Integer.parseInt(st.nextToken());
                if (cell > 0) {
                    map[350 + i][350 + j] = cell;
                    pq.add(new Cell(350 + i, 350 + j, cell, cell));
                }
            }
        }
    }

    static void start() {
        int time = 0;
        Queue<Cell> q = new LinkedList<>(); // 임시 큐

        while (time < K) {
            while (!pq.isEmpty()) {
                Cell c = pq.poll();
                c.hp--;

                if (c.hp == -1) { // 활성화 상태인 경우 (번식O)
                    for (int t = 0; t < 4; t++) {
                        int nr = c.r + dr[t];
                        int nc = c.c + dc[t];

                        if (!visited[nr][nc] && map[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            q.offer(new Cell(nr, nc, c.w, c.w));
                        }
                    }
                }

                if (c.w + c.hp == 0) continue; // 죽은 세포

                q.offer(c); // 그 외는 큐에 넣기
            }

            while (!q.isEmpty()) {
                pq.offer(q.poll());
            }

            time++;
        }

        ans = pq.size();
    }
}