import java.util.*;
import java.io.*;

public class Main {
    static final int LENGTH = 19;
    static int stone, ansR, ansC;
    static int map[][];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        judge(); // 오목

        System.out.println(stone);
        if (stone > 0) // 승부가 난 경우에만 가장 왼쪽 돌 좌표 출력
            System.out.println((ansR + 1) + " " + (ansC + 1));
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[LENGTH][LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < LENGTH; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void judge() {
        for (int j = 0; j < LENGTH; j++)
            for (int i = 0; i < LENGTH; i++) {
                if (map[i][j] > 0) { // 돌인 경우
                    stone = map[i][j]; // 1 = 검정 바둑알, 2 = 흰 바둑알
                    if (isWin(i, j) == 4) {
                        ansR = i;
                        ansC = j;
                        return;
                    } else stone = 0;
                }
            }
    }

    static int isWin(int r, int c) {
        int cnt1 = 0; // | 세로
        int cnt2 = 0; // ㅡ 가로
        int cnt3 = 0; // \ 대각
        int cnt4 = 0; // / 대각

        // 연속한 돌 개수 세기
        for (int i = 1; i < 5; i++)
            if (check(r + i, c))
                if (map[r + i][c] == stone)
                    cnt1++;

        for (int j = 1; j < 5; j++)
            if (check(r, c + j))
                if (map[r][c + j] == stone)
                    cnt2++;

        for (int i = 1; i < 5; i++)
            if (check(r + i, c + i))
                if (map[r + i][c + i] == stone)
                    cnt3++;

        for (int i = 1; i < 5; i++)
            if (check(r - i, c + i))
                if (map[r - i][c + i] == stone)
                    cnt4++;

        // 예외: 양 끝 돌의 색깔 확인
        if (cnt1 == 4)
            if (!(check(r - 1, c) && map[r - 1][c] == stone) && !(check(r + 5, c) && map[r + 5][c] == stone))
                return cnt1;

        if (cnt2 == 4)
            if (!(check(r, c - 1) && map[r][c - 1] == stone) && !(check(r, c + 5) && map[r][c + 5] == stone))
                return cnt2;

        if (cnt3 == 4)
            if (!(check(r - 1, c - 1) && map[r - 1][c - 1] == stone) && !(check(r + 5, c + 5) && map[r + 5][c + 5] == stone))
                return cnt3;

        if (cnt4 == 4)
            if (!(check(r + 1, c - 1) && map[r + 1][c - 1] == stone) && !(check(r - 5, c + 5) && map[r - 5][c + 5] == stone))
                return cnt4;

        return 0;
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < LENGTH && 0 <= c && c < LENGTH;
    }
}