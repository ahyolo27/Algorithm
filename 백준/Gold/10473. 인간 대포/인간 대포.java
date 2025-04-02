import java.io.*;
import java.util.*;

public class Main {
    static final double INF = Double.MAX_VALUE;
    static int N;
    static double startX, startY, endX, endY, cannons[][], weight[];
    static List<Node> graph[];

    static class Node {
        int idx;
        double time;

        Node(int idx, double time) {
            this.idx = idx;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setWeight(); // 가중치 설정
        dijkstra(0); // 다익스트라 수행

        System.out.println(weight[N + 1]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        startX = Double.parseDouble(st.nextToken());
        startY = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Double.parseDouble(st.nextToken());
        endY = Double.parseDouble(st.nextToken());

        N = Integer.parseInt(br.readLine());

        cannons = new double[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            cannons[i][0] = Double.parseDouble(st.nextToken());
            cannons[i][1] = Double.parseDouble(st.nextToken());
        }
    }

    static void setWeight() {
        weight = new double[N + 2];
        for (int i = 0; i < N + 2; i++)
            weight[i] = INF;

        graph = new ArrayList[N + 2];
        for (int i = 0; i < N + 2; i++)
            graph[i] = new ArrayList<>();

        // start -> end
        double time = Math.sqrt(Math.pow(startX - endX, 2) + Math.pow(startY - endY, 2)) / 5; // 걷기
        graph[0].add(new Node(N + 1, time));

        // start -> cannons
        for (int i = 1; i <= N; i++) {
            time = Math.sqrt(Math.pow(startX - cannons[i][0], 2) + Math.pow(startY - cannons[i][1], 2)) / 5; // 걷기
            graph[0].add(new Node(i, time));
        }

        // cannons -> cannons
        double distance = 0;
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                distance = Math.sqrt(Math.pow(cannons[i][0] - cannons[j][0], 2) + Math.pow(cannons[i][1] - cannons[j][1], 2));

                if (distance == 50) { // 대포
                    graph[i].add(new Node(j, 2));
                    graph[j].add(new Node(i, 2));
                } else {
                    time = Math.abs(distance - 50) / 5 + 2; // 걷기 + 대포
                    graph[i].add(new Node(j, time));
                    graph[j].add(new Node(i, time));
                }

                time = distance / 5; // 걷기
                graph[i].add(new Node(j, time));
                graph[j].add(new Node(i, time));
            }
        }

        // cannons -> end
        for (int i = 1; i <= N; i++) {
            distance = Math.sqrt(Math.pow(cannons[i][0] - endX, 2) + Math.pow(cannons[i][1] - endY, 2)); // 대포

            if (distance == 50) // 대포
                graph[i].add(new Node(N + 1, 2));
            else {
                time = Math.abs(distance - 50) / 5 + 2; // 걷기 + 대포
                graph[i].add(new Node(N + 1, time));
            }

            time = distance / 5; // 걷기
            graph[i].add(new Node(N + 1, time));
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.time, o2.time));
        pq.offer(new Node(start, 0));
        weight[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.time > weight[node.idx]) continue;

            for (Node next : graph[node.idx]) {
                if (weight[next.idx] > node.time + next.time) {
                    weight[next.idx] = node.time + next.time;
                    pq.offer(new Node(next.idx, weight[next.idx]));
                }
            }
        }
    }
}