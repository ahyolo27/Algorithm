import java.io.*;
import java.util.*;

public class Main {
    static int N, M, degrees[];
    static List<Integer> graph[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 위상정렬

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        degrees = new int[N + 1]; // 진입 차수

        graph = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 1; j < n; j++) {
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                degrees[b]++;
                a = b;
            }
        }
    }

    static void calc() {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        for (int i = 1; i < N + 1; i++) // 진입차수가 0인 노드를 큐에 삽입
            if (degrees[i] == 0) q.add(i);

        while (!q.isEmpty()) {
            int a = q.poll();

            for (int b : graph[a]) {
                degrees[b]--;
                if (degrees[b] == 0) q.add(b);
            }
            ans.add(a);
        }

        if (ans.size() < N)
            sb.append(0);
        else {
            for (int a : ans)
                sb.append(a).append("\n");
        }
    }
}