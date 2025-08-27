import java.util.*;
import java.io.*;

public class Main {
    static int N, M, start, end, weight[];
    static List<Edge> graph[];
    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int node, weight;
        Edge before;

        Edge(int node, int weight, Edge before) {
            this.node = node;
            this.weight = weight;
            this.before = before;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        dijkstra();
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();

        weight = new int[N + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, w, null)); // 단방향
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.add(new Edge(start, 0, null));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Edge n = pq.poll();

            if (n.node == end) { // 도착한 경우
                sb.append(weight[end]).append("\n");
                findRoute(n);
                break;
            }

            if (weight[n.node] < n.weight) continue;

            for (Edge next : graph[n.node]) {
                if (weight[next.node] > weight[n.node] + next.weight) {
                    weight[next.node] = weight[n.node] + next.weight;
                    pq.add(new Edge(next.node, weight[next.node], n));
                }
            }
        }
    }

    static void findRoute(Edge e) { // 역추적
        int cnt = 0;
        Stack<Integer> stack = new Stack<>();

        while (e != null) {
            stack.add(e.node);
            cnt++;
            e = e.before;
        }

        sb.append(cnt).append("\n");
        while(!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
    }
}