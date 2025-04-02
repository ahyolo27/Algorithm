import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int T, N, D, C, weight[], computerCnt, finishTime;
    static List<Node> graph[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int u, s;

        Node(int u, int s) {
            this.u = u;
            this.s = s;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            input(); // 입력
            dijkstra(C); // 다익스트라 시작
            setAnswers();

            sb.append(computerCnt).append(" ").append(finishTime).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        computerCnt = 0; // 초기화
        finishTime = 0;

        weight = new int[N + 1];
        Arrays.fill(weight, INF);

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            graph[b].add(new Node(a, s));
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.s - o2.s);
        pq.offer(new Node(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int i = 0; i < graph[node.u].size(); i++) {
                Node next = graph[node.u].get(i);

                if (weight[next.u] > node.s + next.s) {
                    weight[next.u] = node.s + next.s;
                    pq.offer(new Node(next.u, weight[next.u]));
                }
            }
        }
    }

    static void setAnswers() {
        int cnt = 0;
        int max = 0;

        for (int i = 1; i <= N; i++) {
            if (weight[i] == INF)
                weight[i] = 0;
            else {
                cnt++;
                max = Math.max(weight[i], max);
            }
        }

        computerCnt = cnt;
        finishTime = max;
    }
}