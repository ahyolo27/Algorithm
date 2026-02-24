import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            String num[] = new String[N];
            for (int j = 0; j < N; j++)
                num[j] = br.readLine();

            Arrays.sort(num); // 사전순 정렬

            boolean finished = false;

            for (int j = 0; j < N - 1; j++) {
                if (num[j + 1].startsWith(num[j])) {
                    finished = true;
                    break;
                }
            }

            if (finished)
                sb.append("NO").append("\n");
            else
                sb.append("YES").append("\n");
        }

        System.out.println(sb);
    }
}