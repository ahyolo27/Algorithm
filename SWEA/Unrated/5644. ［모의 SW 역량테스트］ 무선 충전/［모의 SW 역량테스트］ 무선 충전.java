import java.io.*;
import java.util.*;

public class Solution {
    static int T, M, A, a[], b[], BC[][], ans;
    static int aX, aY, bX, bY;
    static int dX[] = {0, 0, 1, 0, -1}, dY[] = {0, -1, 0, 1, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input(); // 입력
            move(); // 이동
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        a = new int[M + 1];
        b = new int[M + 1];
        BC = new int[A][4];
        aX = 1; // 초기화
        aY = 1;
        bX = 10;
        bY = 10;
        ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++)
            a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++)
            b[i] = Integer.parseInt(st.nextToken());

        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++)
                BC[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void move() {
        boolean checkA[] = new boolean[A];
        boolean checkB[] = new boolean[A];

        // 초기화
        for (int i = 0; i < A; i++) {
            if (isIn(aX, aY, BC[i]))
                checkA[i] = true;
            if (isIn(bX, bY, BC[i]))
                checkB[i] = true;
        }
        ans = getPower(checkA, checkB);

        for (int t = 1; t <= M; t++) {
            // 이동
            if (check(aX + dX[a[t]], aY + dY[a[t]])) {
                aX = aX + dX[a[t]];
                aY = aY + dY[a[t]];
            }
            if (check(bX + dX[b[t]], bY + dY[b[t]])) {
                bX = bX + dX[b[t]];
                bY = bY + dY[b[t]];
            }

            // BC의 범위에 들었는지 확인
            for (int i = 0; i < A; i++) {
                if (isIn(aX, aY, BC[i]))
                    checkA[i] = true;
                if (isIn(bX, bY, BC[i]))
                    checkB[i] = true;
            }

            // 범위가 서로 겹치는지 확인 후 power 갱신
            ans += getPower(checkA, checkB);

            Arrays.fill(checkA, false);
            Arrays.fill(checkB, false);
        }
    }

    static boolean isIn(int x, int y, int bc[]) { // BC[i]의 범위 안에 있으면 true
        return (Math.abs(x - bc[0]) + Math.abs(y - bc[1]) <= bc[2]);
    }

    private static int getPower(boolean checkA[], boolean checkB[]) {
        int power = 0;
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < A; j++) {
                if (checkA[i] && checkB[j] && i == j) // A와 B가 같은 BC에 있는 경우
                    power = Math.max(BC[i][3], power);
                else if (checkA[i] && checkB[j]) // A와 B 둘다 BC에 있긴 한데 같은 BC는 아닌 경우
                    power = Math.max(BC[i][3] + BC[j][3], power);
                else if (checkA[i]) // A만 BC에 있는 경우
                    power = Math.max(BC[i][3], power);
                else if (checkB[j]) // B만 BC에 있는 경우
                    power = Math.max(BC[j][3], power);
            }
        }
        return power;
    }

    static boolean check(int x, int y) { // map의 범위를 벗어나지 않으면 true
        return 1 <= x && x <= 10 && 1 <= y && y <= 10;
    }
}