import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, X, weight1[], weight2[], ans;
    static List<Node> graph1[];
    static List<Node> graph2[];

    static class Node {
        int v, w;

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        dijkstra(graph1, weight1); // 파티 가기
        dijkstra(graph2, weight2); // 집 가기
        getMax();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph1 = new ArrayList[N + 1];
        graph2 = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        weight1 = new int[N + 1];
        weight2 = new int[N + 1];
        Arrays.fill(weight1, INF);
        Arrays.fill(weight2, INF);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph1[to].add(new Node(from, time));
            graph2[from].add(new Node(to, time));
        }
    }

    static void dijkstra(List<Node> graph[], int weight[]) {
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.w - o2.w)); // 가중치 오름차순
        pq.offer(new Node(X, 0));
        weight[X] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (weight[now.v] < now.w) continue;

            for (Node next : graph[now.v]) {
                if (weight[next.v] > now.w + next.w) {
                    weight[next.v] = now.w + next.w;
                    pq.offer(new Node(next.v, weight[next.v]));
                }
            }
        }
    }

    static void getMax() {
        int max = -1;
        for (int i = 1; i <= N; i++)
            max = Math.max(weight1[i] + weight2[i], max);
        ans = max;
    }
}