import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int max = -1;
    static int dices[][];

    public static void main(String[] args) throws IOException {

        input(); // 입력

        for (int i = 0; i < 6; i++)
            findMax(1, i, 0); // 주사위 쌓기

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];

        // 주사위 입력 후 배열에 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++)
                dices[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void findMax(int depth, int downIdx, int sum) {
        int upIdx = -1;
        int tmpMax = 0;

        // 반대쪽 면 찾기
        switch (downIdx) {
            case 0:
                upIdx = 5;
                break;
            case 1:
                upIdx = 3;
                break;
            case 2:
                upIdx = 4;
                break;
            case 3:
                upIdx = 1;
                break;
            case 4:
                upIdx = 2;
                break;
            case 5:
                upIdx = 0;
                break;
        }

        for (int i = 0; i < 6; i++)
            if (!(i == upIdx || i == downIdx)) // 옆면이면 최댓값 찾기
                tmpMax = Math.max(tmpMax, dices[depth - 1][i]);
        sum += tmpMax;

        if (depth == N) { // 더이상 주사위가 없으면
            max = Math.max(max, sum);
            return;
        } else { // 있으면 다음 주사위 밑면 찾아서 반복
            for (int i = 0; i < 6; i++) {
                if (dices[depth - 1][upIdx] == dices[depth][i]) {
                    findMax(depth + 1, i, sum);
                    break;
                }
            }
        }
    }
}