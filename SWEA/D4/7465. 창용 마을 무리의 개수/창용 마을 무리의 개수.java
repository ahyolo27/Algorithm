import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, cnt;
    static List<Integer> graph[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            calc(); // 무리 개수 세기

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cnt = 0;
        graph = new ArrayList[N + 1]; // 인접리스트 초기화
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void calc() {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                q.add(i);
                visited[i] = true;

                while (!q.isEmpty()) {
                    int node = q.poll();
                    if (!graph[node].isEmpty()) {
                        for (int j = 0; j < graph[node].size(); j++) { // 현재 노드로부터 연결된 모든 노드 탐색
                            if (!visited[graph[node].get(j)]) { // 무리에 넣지 않은 노드이면 넣고 방문 처리
                                visited[graph[node].get(j)] = true;
                                q.add(graph[node].get(j));
                            }
                        }
                    }
                }
                cnt++; // 무리 개수 증가
            }
        }
    }
}