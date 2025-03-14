import java.io.*;
import java.util.*;

public class Main {
    static int N, M, degree[];
    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력

        calc(); // 문제 풀이 순서 계산

        output(); // 출력
        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        degree = new int[N + 1];
        result = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            degree[b]++;
        }
    }

    static void calc() {
        PriorityQueue<Integer> nodes = new PriorityQueue<>(); // 더 쉬운 문제부터 풀이

        // 초기 큐 구성
        for (int i = 1; i <= N; i++)
            if (degree[i] == 0) // 진입차수가 0이면 큐에 삽입
                nodes.offer(i);

        // 정점 처리
        while (!nodes.isEmpty()) {
            int node = nodes.poll();
            result.add(node); // 순서 리스트에 추가

            for (int next : graph.get(node)) { // 인접 정점들의 진입 차수 -1
                degree[next]--;
                if (degree[next] == 0) // 진입 차수가 0이면 큐에 삽입
                    nodes.offer(next);
            }
        }
    }

    static void output() {
        for (int n : result)
            sb.append(n).append(" ");
    }
}