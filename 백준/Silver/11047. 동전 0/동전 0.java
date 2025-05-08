import java.io.*;
import java.util.*;

public class Main {
    static int N, K, coin[], cnt;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 동전 개수 세기

        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coin = new int[N];
        for (int i = 0; i < N; i++)
            coin[i] = Integer.parseInt(br.readLine());
    }

    static void calc() {
        int idx = N - 1;
        while (K != 0) {
            cnt += K / coin[idx];
            K %= coin[idx--];
        }
    }
}