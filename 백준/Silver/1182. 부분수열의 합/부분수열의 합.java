import java.util.*;
import java.io.*;

public class Main {
    static int N, S, nums[], ans;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(0, 0, 0); // 부분집합 찾기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
    }

    static void calc(int depth, int cnt, int sum) {
        if (depth == N) {
            if (sum == S && cnt > 0)
                ans++;
            return;
        }

        calc(depth + 1, cnt + 1, sum + nums[depth]); // 뽑는 경우
        calc(depth + 1, cnt, sum); // 뽑지 않는 경우
    }
}