import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE;
    static int N, M;
    static long weight[];
    static boolean node[];
    static List<Node> graph[];

    static class Node {
        int v;
        long w;

        Node(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        dijkstra(); // 최단경로 탐색

        System.out.println(weight[N - 1] == INF ? -1 : weight[N - 1]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        node = new boolean[N];
        weight = new long[N];
        graph = new ArrayList[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            node[i] = Integer.parseInt(st.nextToken()) == 0;
            weight[i] = INF;
            graph[i] = new ArrayList<>();
        }
        node[N - 1] = true; // 계산의 편리를 위해 넥서스도 시야에 보이지 않는 것으로 처리

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, t));
            graph[b].add(new Node(a, t));
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.w));
        pq.offer(new Node(0, 0));
        weight[0] = 0;

        while (!pq.isEmpty()) {
            Node n = pq.poll();

            if (weight[n.v] < n.w) continue; // 가중치 갱신 필요 없는 경우 continue

            for (Node next : graph[n.v]) {
                if (!node[next.v]) continue; // 가려는 노드가 시야에 보이는 경우 continue

                if (weight[next.v] > weight[n.v] + next.w) {
                    weight[next.v] = weight[n.v] + next.w;
                    pq.offer(new Node(next.v, weight[next.v]));
                }
            }
        }
    }
}