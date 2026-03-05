import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        int N = Integer.parseInt(br.readLine());
        int num[] = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());

        // solution
        int cnt = 0;
        for (int i = 1; i < N; i++) {
            if (num[i - 1] > num[i]) {
                int k = (int) Math.ceil(Math.log((double) num[i - 1] / num[i]) / Math.log(2));
                cnt += k;
                num[i] *= (int) Math.pow(2, k);
            }
        }

        // output
        System.out.println(cnt);
    }
}