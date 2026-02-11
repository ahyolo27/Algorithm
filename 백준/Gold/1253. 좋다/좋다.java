import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, ans;
    static long num[];

    public static void main(String[] args) throws IOException {

        input();

        calc();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        num = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            num[i] = Long.parseLong(st.nextToken());

        Arrays.sort(num);
    }

    static void calc() {
        for (int k = 0; k < N; k++) {
            int left = 0;
            int right = N - 1;

            while (left < right) {
                if (left == k) {
                    left++;
                    continue;
                }

                if (right == k) {
                    right--;
                    continue;
                }

                long sum = num[left] + num[right];

                if (sum == num[k]) {
                    ans++;
                    break;
                }

                if (sum < num[k]) left++;
                else right--;
            }
        }
    }
}