import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, cX, cY, hX, hY, consumers[][], min;
    static boolean isSelected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            calc(0, cX, cY, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        consumers = new int[N][2];
        isSelected = new boolean[N];
        min = Integer.MAX_VALUE; // 초기화

        st = new StringTokenizer(br.readLine());
        cX = Integer.parseInt(st.nextToken()); // 회사의 좌표
        cY = Integer.parseInt(st.nextToken());
        hX = Integer.parseInt(st.nextToken()); // 집의 좌표
        hY = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            consumers[i][0] = Integer.parseInt(st.nextToken()); // 고객의 좌표
            consumers[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    static void calc(int depth, int x, int y, int distance) {
        if (depth == N) {
            distance += getDistance(x, y, hX, hY);
            min = Math.min(distance, min);
            return;
        }
        if (distance > min) // min보다 커지면 어차피 더이상 볼 필요 없으므로 탐색 중단
            return;

        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                calc(depth + 1, consumers[i][0], consumers[i][1], distance + getDistance(x, y, consumers[i][0], consumers[i][1]));
                isSelected[i] = false;
            }
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}