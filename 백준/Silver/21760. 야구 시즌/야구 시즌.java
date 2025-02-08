import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int ans[];
    static Queue<Game> games = new LinkedList<>();

    static class Game {
        int n, m, k, d;

        Game(int n, int m, int k, int d) {
            this.n = n;
            this.m = m;
            this.k = k;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        play(); // 야구 시즌 시작

        for (int t = 0; t < T; t++)
            System.out.println(ans[t]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            games.add(new Game(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        ans = new int[T];
    }

    static void play() {
        int idx = 0;
        while (!games.isEmpty()) {
            Game g = games.poll();

            int same = g.n * (g.m * (g.m - 1) / 2); // 같은 리그
            int diff = g.n * (g.n - 1) / 2 * (int)Math.pow(g.m, 2); // 다른 리그

            int B = g.d / (g.k * same + diff);

            if (B == 0) // 해가 존재하지 않는 경우
                ans[idx++] = -1;
            else {
                int A = g.k * B;
                ans[idx++] = A * same + B * diff;
            }
        }
    }
}
