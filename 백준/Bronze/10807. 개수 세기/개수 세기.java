import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int list[] = new int[n];
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            list[i] = Integer.parseInt(st.nextToken());

        int v = Integer.parseInt(br.readLine());
        for (int i = 0; i < list.length; i++)
            if (v == list[i]) ans++;

        System.out.println(ans);
    }
}