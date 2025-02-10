import java.util.*;
import java.io.*;

public class Main {
    static int N, V[];
    static long sum;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        play(); // 스케이트 타기

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        V = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            V[i] = Integer.parseInt(st.nextToken());
    }

    static void play() {
        V[N - 1] = 1; // 마지막에 0으로 속력을 줄여야 하므로 마지막 중간 지점의 속도는 반드시 1
        for (int i = N - 1; i > 0; i--) {
            if (V[i - 1] > V[i])
                V[i - 1] = V[i] + 1;
        }

        for (int i = 0; i < N; i++)
            sum += V[i];
    }
}