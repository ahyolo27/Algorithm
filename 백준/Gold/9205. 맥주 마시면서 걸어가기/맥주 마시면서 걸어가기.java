import java.io.*;
import java.util.*;

public class Main {
    static int T, N, nodes[][];
    static boolean isSelected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static class Pos {
        int x, y, idx;

        Pos(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            input(); // 입력
            bfs();
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        nodes = new int[N + 2][2];
        isSelected = new boolean[N + 2];

        for (int i = 0; i < N + 2; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i][0] = Integer.parseInt(st.nextToken());
            nodes[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void bfs() {
        Queue<Pos> q = new LinkedList<>();

        int startX = nodes[0][0];
        int startY = nodes[0][1];
        isSelected[0] = true;

        for (int i = 1; i < nodes.length; i++)
            if (getDistance(startX, startY, nodes[i][0], nodes[i][1]) <= 1000)
                q.offer(new Pos(nodes[i][0], nodes[i][1], i));

        while (!q.isEmpty()) {
            Pos p = q.poll();
            isSelected[p.idx] = true;

            if (p.idx == N + 1) {
                sb.append("happy").append("\n");
                return;
            }

            for (int i = 0; i < nodes.length; i++) {
                if (!isSelected[i])
                    if (getDistance(p.x, p.y, nodes[i][0], nodes[i][1]) <= 1000)
                        q.offer(new Pos(nodes[i][0], nodes[i][1], i));
            }
        }

        sb.append("sad").append("\n");
    }

    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}