import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, A[], B[];
    static long K, X, Y, sum;

    public static void main(String[] args) throws IOException {
        input(); // 입력
        calc();

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void calc() {
        long score[] = new long[N];

        for (int i = 0; i < N; i++)
            score[i] = X * A[i] - Y * B[i];

        Arrays.sort(score);

        sum = K * (X + Y); // 기본값
        for (int i = 0; i < M; i++) // 증감분 적용
            sum += score[i];
    }
}