import java.util.*;
import java.io.*;

public class Main {
    static int N, M, rides[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        search();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        rides = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            rides[i] = Integer.parseInt(st.nextToken());
    }

    static void search() {
        if (N <= M) {
            System.out.println(N);
            return;
        }

        long left = 0;
        long right = 30L * M * N;
        long time = 0;

        // 1. 조건을 만족하는 시간 찾기
        while (left <= right) {
            long mid = (left + right) / 2;

            long sum = M;
            for (int i = 0; i < M; i++)
                sum += mid / rides[i]; // 한 놀이기구가 time 안에 태울 수 있는 사람 수

            if (sum >= N) {
                time = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        // 2. time-1 까지 탈 수 있는 아이들 수 세기
        long children = M;
        for (int i = 0; i < M; i++)
            children += (time - 1) / rides[i];

        // 3. 딱 time에 비는 놀이기구 중 N번째 아이가 타는 놀이기구의 인덱스 찾기
        for (int i = 0; i < M; i++) {
            if (time % rides[i] == 0) {
                children++;
                if (children == N) {
                    System.out.println(i + 1);
                    return;
                }
            }
        }
    }
}