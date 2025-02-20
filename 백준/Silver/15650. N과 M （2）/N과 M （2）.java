import java.io.*;
import java.util.*;

public class Main {
    static int N, M, arr[];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        set(1, 0);

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
    }

    static void set(int start, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++)
                sb.append(arr[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[depth] = i;
            set(i + 1, depth + 1);
        }
    }
}