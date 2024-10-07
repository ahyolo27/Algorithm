import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        long ans[] = new long[t];

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n == 1 || n == 2 || n == 3)
                ans[i] = 1;
            else {
                long tri[] = new long[n];
                tri[0] = 1;
                tri[1] = 1;
                tri[2] = 1;

                for (int j = 3; j < n; j++) {
                    tri[j] = tri[j - 3] + tri[j - 2];
                }

                ans[i] = tri[n - 1];
            }
        }

        for (int i = 0; i < ans.length; i++)
            System.out.println(ans[i]);

    }
}