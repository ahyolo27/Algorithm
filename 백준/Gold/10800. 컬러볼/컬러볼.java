import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Ball {
        int idx, color, size;

        Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        int N = Integer.parseInt(br.readLine());

        int maxColor = 0;

        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());

            balls[i] = (new Ball(i, color, size));
            maxColor = Math.max(color, maxColor);
        }

        // solution
        StringBuilder sb = new StringBuilder();

        // 0. 누적합 배열
        long total = 0;
        long sum[] = new long[maxColor + 1]; // 색 별 누적합
        long ans[] = new long[N];

        // 1. 크기 기준 정렬
        Arrays.sort(balls, (o1, o2) -> o1.size - o2.size);

        // 2. 계산
        int j = 0;
        for (int i = 0; i < N; i++) {
            int size = balls[i].size;

            while (balls[j].size < size) {
                Ball b = balls[j];
                total += b.size;
                sum[b.color] += b.size;
                j++;
            }

            ans[balls[i].idx] = total - sum[balls[i].color];
        }

        // output
        for (long answer : ans)
            sb.append(answer).append("\n");
        System.out.println(sb);
    }
}