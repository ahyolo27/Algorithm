import java.io.*;
import java.util.*;

public class Main {
    static char command[] = {'D', 'S', 'L', 'R'};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            search(A, B); // 계산 시작
        }

        System.out.println(sb);
    }

    static void search(int A, int B) {
        String c[] = new String[10000];
        Arrays.fill(c, null);

        Queue<Integer> q = new LinkedList<>();
        q.offer(A);
        c[A] = "";

        while (!q.isEmpty()) {
            int n = q.poll();

            for (int i = 0; i < 4; i++) {
                int next = calc(n, i);

                if (next == B) {
                    sb.append(c[n]).append(command[i]).append("\n");
                    return;
                }

                if (next < 0 || 9999 < next || c[next] != null) continue;

                c[next] = c[n] + command[i];
                q.offer(next);
            }
        }
    }

    static int calc(int a, int sign) { // DSLR 계산기
        int tmp = 0;
        switch (sign) {
            case 0: // D
                a = 2 * a % 10000;
                break;
            case 1: // S
                a = a == 0 ? 9999 : a - 1;
                break;
            case 2: // L
                tmp = a / 1000;
                a = (a % 1000) * 10;
                a += tmp;
                break;
            case 3: // R
                tmp = (((a % 1000) % 100) % 10);
                a = (a - tmp) / 10;
                a += 1000 * tmp;
                break;
        }
        return a;
    }
}