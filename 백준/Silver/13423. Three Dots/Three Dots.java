import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int cnt;
        for (int t = 0; t < T; t++) {

            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int arr[] = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(st.nextToken());
            Arrays.sort(arr); // 오름차순 정렬

            cnt = 0;

            // 투포인터
            for (int k = 1; k < N - 1; k++) {
                int left = k - 1;
                int right = k + 1;

                while (left >= 0 && right < N) {
                    int sum = arr[left] + arr[right];
                    int target = 2 * arr[k];

                    if (sum == target) {
                        cnt++;
                        left--;
                        right++;
                    } else if (sum < target)
                        right++;
                    else
                        left--;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}