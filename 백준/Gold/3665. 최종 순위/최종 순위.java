import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            //input
            int N = Integer.parseInt(br.readLine());

            int arr[] = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            boolean graph[][] = new boolean[N + 1][N + 1];
            int degrees[] = new int[N + 1]; // 진입 차수

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    graph[arr[i]][arr[j]] = true;
                    degrees[arr[j]]++;
                }
            }

            int M = Integer.parseInt(br.readLine());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (graph[a][b]) {
                    graph[a][b] = false;
                    graph[b][a] = true;
                    degrees[b]--;
                    degrees[a]++;
                } else {
                    graph[b][a] = false;
                    graph[a][b] = true;
                    degrees[a]--;
                    degrees[b]++;
                }
            }

            // solution -- topological sort
            Queue<Integer> q = new LinkedList<>();

            for (int i = 1; i <= N; i++) {
                if (degrees[i] == 0)
                    q.add(i);
            }

            int cnt = 0;
            StringBuilder tmp = new StringBuilder();

            while (!q.isEmpty()) {
                if (q.size() > 1) {
                    tmp = null;
                    break;
                }

                int now = q.poll();
                cnt++;
                tmp.append(now).append(" ");

                for (int next = 1; next <= N; next++) {
                    if (graph[now][next]) {
                        degrees[next]--;
                        if (degrees[next] == 0)
                            q.add(next);
                    }
                }
            }

            if (tmp == null)
                sb.append("?\n");
            else if (cnt < N)
                sb.append("IMPOSSIBLE\n");
            else
                sb.append(tmp).append("\n");
        }

        System.out.println(sb);
    }
}