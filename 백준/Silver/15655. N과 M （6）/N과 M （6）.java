import java.io.*;
import java.util.*;

public class Main {
    static int N, M, list[], ans[];
    static boolean isSelected[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        set(0, 0);

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new int[N];
        ans = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            list[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(list);
        isSelected = new boolean[list[list.length - 1] + 1];
    }

    static void set(int start, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++)
                sb.append(ans[i]).append(" ");
            sb.append('\n');
            return;
        }

        for (int i = start; i < N; i++) {
            ans[depth] = list[i];
            set(i + 1, depth + 1);
        }
    }
}