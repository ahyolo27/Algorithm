import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 세팅
        int list[] = new int[n];
        for (int i = 0; i < n; i++)
            list[i] = i + 1;

        for (int cnt = 0; cnt < m; cnt++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken()) - 1;
            int j = Integer.parseInt(st.nextToken()) - 1;

            int tmp = list[i];
            list[i] = list[j];
            list[j] = tmp;
        }

        for (int i = 0; i < n; i++)
            System.out.print(list[i] + " ");
    }
}