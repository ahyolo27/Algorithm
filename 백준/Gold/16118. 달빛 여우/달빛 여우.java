import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, weightF[], weightW[][], ans;
    static List<Node> graph[];

    static class Node {
        int v, w;
        int state; // wolf state

        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        Node(int v, int w, int state) {
            this.v = v;
            this.w = w;
            this.state = state;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        dijkstraF(); // 여우 다익스트라
        dijkstraW(); // 늑대 다익스트라
        calc(); // 그루터기 개수 세기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weightF = new int[N + 1];
        Arrays.fill(weightF, INF);

        weightW = new int[N + 1][2];
        for (int i = 1; i <= N; i++)
            Arrays.fill(weightW[i], INF);

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, d * 2));
            graph[b].add(new Node(a, d * 2));
        }
    }

    static void dijkstraF() { // 여우 다익스트라
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingDouble(o -> o.w));
        q.offer(new Node(1, 0));
        weightF[1] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (weightF[node.v] < node.w) continue;

            for (int i = 0; i < graph[node.v].size(); i++) {
                Node next = graph[node.v].get(i);
                if (weightF[next.v] > node.w + next.w) {
                    weightF[next.v] = node.w + next.w;
                    q.offer(new Node(next.v, weightF[next.v]));
                }
            }
        }
    }

    static void dijkstraW() { // 늑대 다익스트라
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingDouble(o -> o.w));
        q.offer(new Node(1, 0, 0));
        weightW[1][0] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();
            int nextState = (node.state + 1) % 2;

            if (weightW[node.v][node.state] < node.w) continue;

            for (int i = 0; i < graph[node.v].size(); i++) {
                Node next = graph[node.v].get(i);
                if (nextState % 2 == 1) {
                    if (weightW[next.v][nextState] > node.w + next.w / 2) {
                        weightW[next.v][nextState] = node.w + next.w / 2;
                        q.offer(new Node(next.v, weightW[next.v][nextState], nextState));
                    }
                } else {
                    if (weightW[next.v][nextState] > node.w + next.w * 2) {
                        weightW[next.v][nextState] = node.w + next.w * 2;
                        q.offer(new Node(next.v, weightW[next.v][nextState], nextState));
                    }
                }
            }
        }
    }

    static void calc() {
        for (int i = 1; i <= N; i++) {
            double min = Math.min(weightW[i][0], weightW[i][1]);
            if (min > weightF[i])
                ans++;
        }
    }
}