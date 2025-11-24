import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A[], max;

    public static void main(String[] args) throws IOException {
        input();
        calc();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 사람 수
        M = Integer.parseInt(st.nextToken()); // 필요 풍선 수
        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            max = Math.max(A[i], max);
        }
    }

    static void calc() {
        long left = 0;
        long right = (long) max * M;

        while (left < right) {
            long mid = (left + right) / 2;
            long total = 0;

            for (int a : A) {
                total += mid / a;

                if (total >= M)
                    break;
            }

            if (total >= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}