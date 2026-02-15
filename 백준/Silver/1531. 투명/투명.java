import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int cnt[][] = new int[101][101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int lx = Integer.parseInt(st.nextToken());
            int ly = Integer.parseInt(st.nextToken());
            int rx = Integer.parseInt(st.nextToken());
            int ry = Integer.parseInt(st.nextToken());

            for (int j = lx; j <= rx; j++) {
                for (int k = ly; k <= ry; k++)
                    cnt[j][k]++;
            }
        }


        int answer = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++)
                if (cnt[i][j] > M) answer++;
        }

        System.out.println(answer);
    }
}