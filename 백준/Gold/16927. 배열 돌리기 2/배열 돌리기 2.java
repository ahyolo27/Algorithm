import java.util.*;
import java.io.*;

public class Main {
    static int N, M, R;
    static int array[][], tmp[];
//    static boolean visited[][];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        rotate(); // 배열 돌리기

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++)
                System.out.print(array[i][j] + " ");
            System.out.println();
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        array = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                array[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void rotate() {
        boolean check = true;
        int depth = 0;

        while (true) {
            // 회전 횟수 가공
            int size = N * 2 + M * 2 - 4;
            int r = R % size; // 실제로 이동해야 하는 거리

            int idx = 0;
            tmp = new int[size];

            // 가장자리 순회
            for (int i = depth; i < N - 1 + depth; i++)
                tmp[(idx++ + r) % size] = array[i][depth];

            for (int j = depth; j < M - 1 + depth; j++)
                tmp[(idx++ + r) % size] = array[N - 1 + depth][j];

            for (int i = N - 1 + depth; i > depth; i--)
                tmp[(idx++ + r) % size] = array[i][M - 1 + depth];

            for (int j = M - 1 + depth; j > depth; j--)
                tmp[(idx++ + r) % size] = array[depth][j];

            // 변경값 집어넣기
            idx = 0;
            for (int i = depth; i < N - 1 + depth; i++)
                array[i][depth] = tmp[idx++];

            for (int j = depth; j < M - 1 + depth; j++)
                array[N - 1 + depth][j] = tmp[idx++];

            for (int i = N - 1 + depth; i > depth; i--)
                array[i][M - 1 + depth] = tmp[idx++];

            for (int j = M - 1 + depth; j > depth; j--)
                array[depth][j] = tmp[idx++];

            depth++;
            N -= 2;
            M -= 2;
            if (N < 1 || M < 1)
                break;

        }
    }
}