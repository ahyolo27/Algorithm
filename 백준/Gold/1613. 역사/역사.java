import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1000000;
    static int N, K, S, weight[][];
    static Queue<Pair> pairs = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int a, b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setWeight(); // 가중치 설정
        findRelation(); // 관계 찾기

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        weight = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            Arrays.fill(weight[i], INF);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            weight[a][b] = 1;
        }

        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pairs.offer(new Pair(a, b));
        }
    }

    static void findRelation() {
        while (!pairs.isEmpty()) {
            Pair p = pairs.poll();

            int prev = p.a;
            int next = p.b;

            if (weight[prev][next] == INF && weight[next][prev] == INF)
                sb.append(0).append("\n");
            else if (weight[prev][next] < INF)
                sb.append(-1).append("\n");
            else if (weight[next][prev] < INF)
                sb.append(1).append("\n");
        }
    }

    static void setWeight() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
                }
            }
        }
    }
}