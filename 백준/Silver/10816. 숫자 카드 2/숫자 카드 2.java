import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] count = new int[20000001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            count[Integer.parseInt(st.nextToken()) + 10000000]++;

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            sb.append(count[Integer.parseInt(st.nextToken()) + 10000000]).append(" ");

        System.out.println(sb);
    }
}