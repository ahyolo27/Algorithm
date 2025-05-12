import java.io.*;
import java.util.*;

public class Main {
    static int N, K, ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        if (N != K) calc(); // 시작점이 다른 경우: 시간 계산

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    static void calc() {
        boolean visited[] = new boolean[100001];

        Queue<Integer> q = new LinkedList<>();
        q.offer(N); // start point
        visited[N] = true;

        int time = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int now = q.poll();

                if (now == K) break;

                int next = now + 1;
                if (isValid(next) && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }

                next = now - 1;
                if (isValid(next) && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }

                next = 2 * now;
                if (isValid(next) && !visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }

            if (visited[K]) break;

            time++;
        }

        ans = time;
    }

    static boolean isValid(int n) {
        return 0 <= n && n <= 100000;
    }
}