import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Set<Integer> list = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            list.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        int target[] = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            target[i] = Integer.parseInt(st.nextToken());

        for (int t : target) {
            if (list.contains(t))
                sb.append(1).append("\n");
            else
                sb.append(0).append("\n");
        }

        System.out.println(sb);
    }
}