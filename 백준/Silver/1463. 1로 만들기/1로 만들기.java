import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException { // 예외처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int div[] = new int[n + 1];

        if (n == 1) {
            System.out.println(0);
            return;
        } else if (n == 2) {
            System.out.println(1);
            return;
        } else if (n == 3) {
            System.out.println(1);
            return;
        }

        div[1] = 0;
        div[2] = 1;
        div[3] = 1;

        for (int i = 4; i <= n; i++) {
            if (i % 3 == 0 && i % 2 == 0) {
                int tmp = Math.min(div[i / 3], div[i / 2]);
                div[i] = Math.min(tmp, div[i - 1]) + 1;
            } else if (i % 3 == 0)
                div[i] = Math.min(div[i / 3], div[i - 1]) + 1;
            else if (i % 2 == 0)
                div[i] = Math.min(div[i / 2], div[i - 1]) + 1;
            else
                div[i] = div[i - 1] + 1;
        }

        System.out.println(div[n]);
    }
}