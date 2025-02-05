import java.util.*;
import java.io.*;

public class Main {
    static int W, H, p, q, T;

    public static void main(String[] args) throws IOException {

        input(); // 입력

        move(); // 개미 이동

        System.out.println(p + " " + q);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        T = Integer.parseInt(br.readLine());
    }

    static void move() {
        p = (p + T) % (2 * W);
        q = (q + T) % (2 * H);

        // 반대 방향인 경우(왼쪽)
        if (p > W) p = 2 * W - p;
        if (q > H) q = 2 * H - q;
    }

    static boolean check(int r, int c) { // 경계값이면 true 리턴
        return 0 == r || r == H || 0 == c || c == W;
    }
}