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
        long sum = 0;
        long count[] = new long[N];

        for (int i = 1; i < N; i++) {
            double ratio = Math.ceil(Math.log((double) num[i - 1] / num[i]) / Math.log(2)) + count[i - 1];
            count[i] = Math.max(0, (long) ratio);
            sum += count[i];
        }

        // output
        System.out.println(sum);
    }
}