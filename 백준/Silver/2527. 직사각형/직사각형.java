import java.util.*;
import java.io.*;

public class Main {
    static char ans[] = new char[4];
    static Queue<Pos> squares = new LinkedList<>();

    static class Pos {
        int r, c, p, q;

        Pos(int c, int r, int q, int p) {
            this.c = c;
            this.r = r;
            this.q = q;
            this.p = p;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        check(); // 겹치는지 체크

        for (int i = 0; i < 4; i++)
            System.out.println(ans[i]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            // (c, r) (q, p)
            squares.offer(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            squares.offer(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void check() {
        int idx = 0;

        for (int i = 0; i < 4; i++) {
            char code = ' ';

            Pos p1 = squares.poll();
            Pos p2 = squares.poll();

            if (p1.p < p2.r || p1.q < p2.c || p2.p < p1.r || p2.q < p1.c) // 겹치지 않는 경우
                code = 'd';
            else if ((p1.c == p2.q && p1.p == p2.r) || (p1.q == p2.c && p1.p == p2.r) ||
                    (p1.c == p2.q && p1.r == p2.p) || (p1.q == p2.c && p1.r == p2.p)) // 점에서 만나는 경우
                code = 'c';
            else if (p1.c == p2.q || p1.q == p2.c || p1.r == p2.p || p1.p == p2.r) // 선에서 만나는 경우
                code = 'b';
            else // 겹치는 경우(직사각형)
                code = 'a';

            ans[idx] = code;
            idx++;
        }
    }
}