import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, L, K, ans;
    static List<Pos> stars = new ArrayList<>();

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        input();
        calc();

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());


        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Pos(x, y));
        }

        ans = K; // init
    }

    static void calc() {
        for (Pos a : stars) {
            for (Pos b : stars) {

                int x = a.x;
                int y = b.y;

                int cnt = 0;

                for (Pos star : stars)
                    if (x <= star.x && star.x <= x + L && y <= star.y && star.y <= y + L)
                        cnt++;

                ans = Math.min(ans, K - cnt); // 남은 개수
            }
        }
    }
}