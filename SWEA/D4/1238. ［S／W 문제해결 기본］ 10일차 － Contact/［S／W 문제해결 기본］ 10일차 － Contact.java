import java.io.*;
import java.util.*;

public class Solution {
    static int M, start, ans;
    static boolean visited[];
    static List<Integer> graph[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            input();
            call();

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        ans = 0; // 초기화
        graph = new ArrayList[101];
        visited = new boolean[101];

        for (int i = 1; i <= 100; i++)
            graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M / 2; i++) {
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
        }
    }

    static void call() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int max = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean noCall = true;

            for (int t = 1; t <= size; t++) {
                int node = q.poll();

                for (int i = 0; i < graph[node].size(); i++) {
                    int next = graph[node].get(i);
                    if (!visited[next]) {
                        visited[next] = true;
                        q.offer(next);
                        max = Math.max(next, max);
                        noCall = false;
                    }
                }
            }

            if (noCall) return; // 걸 사람이 없는 경우 종료

            ans = max;
            max = 0; // 초기화
        }
    }
}