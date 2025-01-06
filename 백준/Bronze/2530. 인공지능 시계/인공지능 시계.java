import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(br.readLine());

        int h = 0;
        int m = 0;
        int s = 0;

        if (d >= 60) {
            m = d / 60;
            s = d % 60;
            if (m >= 60) {
                h = m / 60;
                m = m % 60;
                if (h >= 24)
                    h = h % 24;
            }
        } else
            s = d;

        a += h;
        b += m;
        c += s;
        if (c >= 60) {
            b += c / 60;
            c = c % 60;
        }
        if (b >= 60) {
            a += b / 60;
            b = b % 60;
        }
        if (a >= 24) {
            a = a % 24;
        }

        System.out.println(a + " " + b + " " + c);
    }
}