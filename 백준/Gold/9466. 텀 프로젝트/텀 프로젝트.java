import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer;
    static Map<Integer, Integer> graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {

            // input
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            graph = new HashMap<>(); // 초기화

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++)
                graph.put(i, Integer.parseInt(st.nextToken()));

            // calculate
            answer = N; // 초기화
            calc(N, graph);
            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }

    static void calc(int N, Map<Integer, Integer> graph) {
        boolean visited[] = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i])
                dfs(i, visited, graph, new ArrayList<>());
        }
    }

    static void dfs(int now, boolean visited[], Map<Integer, Integer> graph, List<Integer> list) {
        visited[now] = true;
        list.add(now);

        int next = graph.get(now);
        if (!visited[next])
            dfs(next, visited, graph, list);
        else {
            if (list.contains(next)) { // 싸이클인 경우
                int endIdx = list.indexOf(next);
                for (int i = 0; i < endIdx; i++)
                    visited[list.get(i)] = false; // 원상복구
                answer -= list.size() - endIdx;
            }
        }
    }
}