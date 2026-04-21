import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];

        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());
        Arrays.sort(arr);

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N - 1; i++)
            for (int j = i; j < N; j++)
                list.add(arr[i] + arr[j]); // a+b
        Collections.sort(list);

        int max = 0;
        int k, c;

        for (int i = N - 1; i >= 0; i--) {
            k = arr[i];

            for (int j = 0; j < i; j++) { // c > k 인 경우 target이 음수가 되더 의미 없음
                c = arr[j];

                int target = k - c; // target 탐색 -> 이분탐색

                int left = 0, right = list.size() - 1;
                while (left <= right) {
                    int mid = (left + right) / 2;

                    if (target == list.get(mid)) {
                        max = Math.max(k, max);
                        break;
                    } else if (target > list.get(mid))
                        left = mid +1;
                    else
                        right= mid-1;

                }
            }
        }

        System.out.println(max);
    }
}