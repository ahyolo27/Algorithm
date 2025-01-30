import java.util.*;
import java.io.*;

public class Main {
    static int R, C, K, cnt;
    static int ansX, ansY;
    static boolean visited[][];

    public static void main(String[] args) throws IOException {

        input(); // 입력

        move(); // 자리 배정

        if (cnt < K) // 좌석이 부족한 경우
            System.out.println(0);
        else
            System.out.println(++ansX + " " + ++ansY);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        visited = new boolean[R][C];
    }

    static void move() {
        int r = 0;
        int c = 0;

        while (true) {
            for (; c < C; c++) {
                if (visited[r][c])
                    break;
                else {
                    cnt++;
                    if (cnt == K) {
                        ansX = r;
                        ansY = c;
                        return;
                    }
                    visited[r][c] = true;
                }
            }

            r++;
            c--;
            if (checkFin())
                break;

            for (; r < R; r++) {
                if (visited[r][c])
                    break;
                else {
                    cnt++;
                    if (cnt == K) {
                        ansX = r;
                        ansY = c;
                        return;
                    }
                    visited[r][c] = true;
                }
            }

            r--;
            c--;
            if (checkFin())
                break;

            for (; c >= 0; c--) {
                if (visited[r][c])
                    break;
                else {
                    cnt++;
                    if (cnt == K) {
                        ansX = r;
                        ansY = c;
                        return;
                    }
                    visited[r][c] = true;
                }
            }

            r--;
            c++;
            if (checkFin())
                break;

            for (; r >= 0; r--) {
                if (visited[r][c])
                    break;
                else {
                    cnt++;
                    if (cnt == K) {
                        ansX = r;
                        ansY = c;
                        return;
                    }
                    visited[r][c] = true;
                }
            }

            r++;
            c++;
            if (checkFin())
                break;
        }
    }

    static boolean checkFin() {
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                if (visited[i][j])
                    cnt++;
        }
        if (R * C == cnt)
            return true;
        else
            return false;
    }
}