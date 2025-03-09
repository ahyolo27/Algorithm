import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static Map<Integer, List<Integer>> graph = new HashMap<>();
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        sort(); // 정렬
        output(); // 출력

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) // graph 초기화: 정점들에 대한 빈 리스트 생성
            graph.put(i, new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
        }
    }

    static void sort() {
        int degree[] = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            List<Integer> nodes = graph.get(i);
            for (int j = 0; j < nodes.size(); j++) {
                int node = nodes.get(j);
                degree[node]++;
            }
        }

        Queue<Integer> q = new LinkedList<>(); // 진입차수가 0인 정점을 담을 큐
        for (int i = 1; i <= N; i++)
            if (degree[i] == 0)
                q.offer(i);

        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            List<Integer> nodes = graph.get(now);
            if (nodes != null) {
                for (int i = 0; i < nodes.size(); i++) {
                    int node = nodes.get(i);
                    degree[node]--;
                    if (degree[node] == 0)
                        q.offer(node);
                }
            }
        }
    }

    static void output() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.size(); i++)
            sb.append(result.get(i)).append(" ");
        System.out.println(sb);
    }
}
