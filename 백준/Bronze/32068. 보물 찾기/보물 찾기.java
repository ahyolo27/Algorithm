import java.io.*;
import java.util.*;

public class Main {
    static int T, L, R, S, cnt;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            input(); // 입력
            calc(); // 보물찾기 횟수 계산

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        cnt = 1; // 초기화
    }

    static void calc() {
        if ((R - S) > (S - L)) // 가까운거 선택해서 cnt 계산
            cnt += 2 * (S - L);
        else
            cnt += 2 * (R - S) - 1;
    }
}