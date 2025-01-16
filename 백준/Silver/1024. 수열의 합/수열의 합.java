import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        while (true) {
            int min = n / l - ((l - 1) / 2);
            if (l > 100 || min < 0) {
                System.out.println(-1);
                System.exit(0);
            }

            int sum = (min * 2 + l - 1) * l / 2;
            if (sum == n) {
                for (int i = 0; i < l; i++)
                    System.out.print((min + i) + " ");
                System.exit(0);
            }
            l++;
        }
    }
}