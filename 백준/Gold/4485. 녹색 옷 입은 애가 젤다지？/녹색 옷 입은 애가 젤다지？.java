import java.util.*;
import java.io.*;


public class Main {
    static int N;
    static int map[][], rupee[][];
    static boolean visited[][];
    static BufferedReader br;
    static PriorityQueue<Node> pq;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};


    static class Node implements Comparable<Node> {
        int r, c; // 다음 노드
        int weight; // 가중치

        Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;  // 가중치에 따른 오름차순 정렬(우선순위 큐의 기준도 됨)
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 0; ; t++) {

            N = Integer.parseInt(br.readLine());
            if (N == 0) return; // 프로그램 종료

            input(); // 입력
            loseRupee(); // 도둑루피(잃는 루피의 양) 계산

            System.out.println("Problem " + (t + 1) + ": " + rupee[N - 1][N - 1]);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        map = new int[N][N];
        visited = new boolean[N][N];
        rupee = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rupee[i][j] = Integer.MAX_VALUE; // 루피 초기화
            }
        }
    }

    static void loseRupee() {
        pq = new PriorityQueue<>(); // 우선순위 큐
        rupee[0][0] = map[0][0]; // 시작점 초기화
        visited[0][0] = true;

        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int r = node.r;
            int c = node.c;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (check(nr, nc) && !visited[nr][nc]) {
                    rupee[nr][nc] = Math.min(rupee[nr][nc], rupee[r][c] + map[nr][nc]);
                    visited[nr][nc] = true;
                    pq.offer(new Node(nr, nc, rupee[nr][nc]));
                }
            }
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}