import java.util.*;
import java.io.*;

public class Solution {
    static int T, N, map[][], min;
    static ArrayList<Integer> list = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            select(0);
            System.out.println("#" + t + " " + min);
        }
    }

    static void input() throws IOException {
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        min = Integer.MAX_VALUE; // 초기화
        list.clear();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void select(int depth) {
        if (depth == N) {
            if (list.size() == N / 2) {
                int sum1 = 0;
                int sum2 = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (list.contains(i) && list.contains(j))
                            sum1 += map[i][j];
                        else if (!list.contains(i) && !list.contains(j))
                            sum2 += map[i][j];
                    }
                }
                min = Math.min(Math.abs(sum1 - sum2), min);
            }
            return;
        }

        list.add(depth);
        select(depth + 1);
        list.remove((Integer) depth);
        select(depth + 1);
    }
}