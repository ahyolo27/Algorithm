import java.util.*;
import java.io.*;

public class Main {
    static int N, D, K, C, sushi[], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        getSushi();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        sushi = new int[N];
        for (int i = 0; i < N; i++)
            sushi[i] = Integer.parseInt(br.readLine());
    }

    static void getSushi() {
        int count[] = new int[D + 1];
        int cnt = 0;

        for (int i = 0; i < K; i++)
            if (count[sushi[i]]++ == 0)
                cnt++;
        ans = cnt + (count[C] == 0 ? 1 : 0);

        for (int i = 1; i < N; i++) {
            int removeIdx = sushi[i - 1];
            if (--count[removeIdx] == 0)
                cnt--;

            int addIdx = sushi[(i + K - 1) % N];
            if (count[addIdx]++ == 0)
                cnt++;

            int tmp = cnt + (count[C] == 0 ? 1 : 0);
            ans = Math.max(ans, tmp);
        }
    }
}