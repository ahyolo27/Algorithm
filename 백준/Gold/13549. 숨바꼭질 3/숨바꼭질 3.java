import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 현재 수빈이의 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치
        final int INF = Integer.MAX_VALUE;

        if (N == K) { // 처음부터 같은 위치이면 바로 종료
            System.out.println(0);
            return;
        }

        int weight[] = new int[100001];
        Arrays.fill(weight, INF);
        weight[N] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(N); // 큐에 현재 위치 삽입

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) { // 도착한 경우 정답 출력 후 탐색 종료
                System.out.println(weight[now]);
                break;
            }

            /* 범위 내의 값이고 방문하지 않은 경우 탐색 시작 */
            int next = now * 2;
            if (0 <= next && next < 100001 && weight[next] > weight[now]) {
                weight[next] = weight[now];
                q.offer(next);
            }
            next = now + 1;
            if (0 <= next && next < 100001 && weight[next] > weight[now] + 1) {
                weight[next] = weight[now] + 1;
                q.offer(next);
            }
            next = now - 1;
            if (0 <= next && next < 100001 && weight[next] > weight[now] + 1) {
                weight[next] = weight[now] + 1;
                q.offer(next);
            }
        }
    }
}