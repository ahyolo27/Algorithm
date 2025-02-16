import java.io.*;
import java.util.*;

public class Main {
    static int N, M, ans[];
    static boolean isSelected[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        set(0);

    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isSelected = new boolean[N + 1];
        ans = new int[M];
    }

    static void set(int cnt) {
        if (cnt == M) {
            for (int i = 0; i < M; i++)
                System.out.print(ans[i] + " ");
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;
                ans[cnt] = i;
                set(cnt + 1);
                isSelected[i] = false;
            }
        }
    }
}