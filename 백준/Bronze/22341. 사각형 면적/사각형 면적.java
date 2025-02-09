import java.io.*;
import java.util.*;

public class Main {
    static int N, C, A, B;
    static Queue<Pos> list = new LinkedList<>();

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        cut(); // 종이 자르기

        System.out.println(A * B);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 종이의 크기
        C = Integer.parseInt(st.nextToken()); // 점의 개수
        A = N;
        B = N;

        for (int tc = 0; tc < C; tc++) {
            st = new StringTokenizer(br.readLine());
            list.offer(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void cut() {
        while (!list.isEmpty()) {
            Pos p = list.poll();

            if (!check(p.x, p.y))  // 범위 내 값이 아닌 경우
                continue;

            int max1 = p.x * B; // 가로
            int max2 = A * p.y; // 세로

            if (max1 >= max2)
                A = p.x;
            else
                B = p.y;
        }
    }

    static boolean check(int x, int y) {
        return 0 <= x && x < A && 0 <= y && y < B;
    }
}
