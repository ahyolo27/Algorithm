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
            int target;

            for (int i = 0; i < N - 2; i++) {
                for (int j = i + 2; j < N; j++) {
                    if ((arr[i] + arr[j]) % 2 != 0) continue; // 값을 찾을 수 없는 경우

                    target = (arr[i] + arr[j]) / 2;

                    // 이분탐색
                    int left = i + 1;
                    int right = j - 1;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (arr[mid] == target) {
                            cnt++;
                            break;
                        }

                        if (arr[mid] < target)
                            left = mid + 1;
                        else
                            right = mid - 1;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}