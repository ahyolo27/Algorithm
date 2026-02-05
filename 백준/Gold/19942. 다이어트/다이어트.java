import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, minimum[], ingredient[][], cost;
    static String selected;

    public static void main(String[] args) throws IOException {
        input();
        makeSet(0, 0, new int[5], new boolean[N]);

        if (cost != Integer.MAX_VALUE) {
            System.out.println(cost);
            System.out.println(selected);
        } else
            System.out.println(-1); // 답이 없는 경우
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        minimum = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            minimum[i] = Integer.parseInt(st.nextToken());

        ingredient = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                ingredient[i][j] = Integer.parseInt(st.nextToken());
        }

        cost = Integer.MAX_VALUE; // init
    }

    static void makeSet(int now, int depth, int total[], boolean num[]) {
        if (depth == N) {

            if (total[4] > cost) return;

            boolean flag = true;

            for (int i = 0; i < 4; i++)
                if (total[i] < minimum[i]) { // 최소 영양 기준 미달
                    flag = false;
                    break;
                }

            if (flag) {
                if (total[4] == cost) {
                    String candidate = makeAnswer(num);
                    if (selected.compareTo(candidate) > 0)
                        selected = candidate;
                } else {
                    cost = total[4];
                    selected = makeAnswer(num);
                }
            }
            return;
        }

        if (total[4] > cost) return;

        // 선택 O
        for (int i = 0; i < 5; i++)
            total[i] += ingredient[now][i];
        num[now] = true;
        makeSet(now + 1, depth + 1, total, num);

        // 선택 X
        for (int i = 0; i < 5; i++)
            total[i] -= ingredient[now][i];
        num[now] = false;
        makeSet(now + 1, depth + 1, total, num);
    }

    static String makeAnswer(boolean num[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (num[i])
                sb.append(i + 1).append(" ");
        }
        return sb.toString();
    }
}