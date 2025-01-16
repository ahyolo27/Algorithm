import java.util.*;
import java.io.*;

import java.util.*;
import java.io.*;

public class Main { // 상자 넣기
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 상자의 개수
        int box[] = new int[n + 1]; // 상자의 크기 리스트

        // 상자의 크기 입력받아 배열에 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            box[i] = Integer.parseInt(st.nextToken());

        // 상자가 1개인 경우
        if (n == 1) {
            System.out.println(1);
            return;
        }

        // 상자가 2개 이상인 경우
        int dp[] = new int[n + 1];
        Arrays.fill(dp, 1); // 배열 초기화
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j < i; j++)
                if (box[i] > box[j])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
        }

        // dp 배열에서 최댓값 구해 출력
        int max = 0;
        for (int val: dp)
            max = Math.max(max, val);
        System.out.println(max);
    }
}