import java.io.*;
import java.util.*;

public class Main {
    static int N, X, days[], max, maxCnt;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 최대 방문자 수 계산

        if (max > 0)
            System.out.println(max + "\n" + maxCnt);
        else
            System.out.println("SAD");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        days = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++)
            days[i] = Integer.parseInt(st.nextToken());
    }

    static void calc() {
        maxCnt = 1;

        // 처음 윈도우
        int sum = 0;
        for (int i = 1; i <= X; i++)
            sum += days[i];
        max = sum;

        for (int i = X + 1; i <= N; i++) { // 슬라이딩 윈도우
            sum = sum + days[i] - days[i - X];

            if (max < sum) {
                max = sum;
                maxCnt = 1;
            } else if (max == sum)
                maxCnt++;
        }
    }
}