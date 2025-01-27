import java.util.*;
import java.io.*;

public class Main {
    static int count, index;
    static int numbers[] = new int[25]; // 사회자가 부르는 수
    static int bingo[][] = new int[5][5]; // 빙고판
    static boolean visited[][] = new boolean[5][5]; // 빙고 중복 방지

    public static void main(String[] args) throws IOException {

        input(); // 세팅
        play(); // 빙고 시작

        System.out.println(index);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 빙고판 세팅
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                bingo[i][j] = Integer.parseInt(st.nextToken());
        }

        // 사회자가 부르는 수 세팅
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++)
                numbers[idx++] = Integer.parseInt(st.nextToken());
        }
    }

    static void play() {
        while (index != 25) {
            int num = numbers[index++];
            for (int i = 0; i < 5; i++) {
                boolean fin = false;
                for (int j = 0; j < 5; j++)
                    if (num == bingo[i][j]) {
                        bingo[i][j] = 0; // 방문 처리
                        fin = true;
                        break;
                    }
                if (fin)
                    break;
            }
            count += check();
            if (count >= 3)
                return;
        }
    }

    static int check() {
        int sum = 0;
        // 대각선
        if ((bingo[0][0] + bingo[1][1] + bingo[2][2] + bingo[3][3] + bingo[4][4] == 0) &&
                !(visited[0][0] && visited[1][1] && visited[2][2] && visited[3][3] && visited[4][4])) {
            visited[0][0] = true;
            visited[1][1] = true;
            visited[2][2] = true;
            visited[3][3] = true;
            visited[4][4] = true;
            sum++;
        }
        if ((bingo[4][0] + bingo[3][1] + bingo[2][2] + bingo[1][3] + bingo[0][4] == 0) &&
                !(visited[4][0] && visited[3][1] && visited[2][2] && visited[1][3] && visited[0][4])) {
            visited[4][0] = true;
            visited[3][1] = true;
            visited[2][2] = true;
            visited[1][3] = true;
            visited[0][4] = true;
            sum++;
        }

        // 가로
        for (int i = 0; i < 5; i++)
            if ((bingo[i][0] + bingo[i][1] + bingo[i][2] + bingo[i][3] + bingo[i][4] == 0) &&
                    !(visited[i][0] && visited[i][1] && visited[i][2] && visited[i][3] && visited[i][4])) {
                visited[i][0] = true;
                visited[i][1] = true;
                visited[i][2] = true;
                visited[i][3] = true;
                visited[i][4] = true;
                sum++;
                break;
            }

        // 세로
        for (int i = 0; i < 5; i++)
            if ((bingo[0][i] + bingo[1][i] + bingo[2][i] + bingo[3][i] + bingo[4][i] == 0) &&
                    !(visited[0][i] && visited[1][i] && visited[2][i] && visited[3][i] && visited[4][i])) {
                visited[0][i] = true;
                visited[1][i] = true;
                visited[2][i] = true;
                visited[3][i] = true;
                visited[4][i] = true;
                sum++;
                break;
            }

        return sum;
    }
}