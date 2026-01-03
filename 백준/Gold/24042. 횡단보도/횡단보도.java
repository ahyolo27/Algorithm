import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int idx;
        long weight;

        Node(int idx, long weight) {
            this.idx = idx;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Node> graph[] = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, i));
            graph[b].add(new Node(a, i));
        }

        // solution

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.weight));
        long weight[] = new long[N + 1];

        Arrays.fill(weight, Long.MAX_VALUE);
        weight[1] = 0;

        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.weight != weight[now.idx]) continue;

            for (Node next : graph[now.idx]) {
                long calc;

                if (weight[now.idx] <= next.weight) { // 바로 갈 수 있는 경우
                    calc = next.weight;
                } else { // 기다려야 하는 경우
                    long diff = weight[now.idx] - next.weight;
                    calc = next.weight + (diff + M - 1) / M * (long) M;
                }

                if (calc < weight[next.idx]) {
                    weight[next.idx] = calc;
                    pq.add(new Node(next.idx, calc));
                }
            }
        }

        System.out.println(weight[N]);
    }
}