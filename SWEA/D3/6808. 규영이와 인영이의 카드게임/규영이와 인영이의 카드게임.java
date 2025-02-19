import java.util.*;
import java.io.*;

public class Solution {
    static int T, win, lose, player1[], player2[];
    static boolean isSelected[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            play(0, 0, 0);

            System.out.println("#" + t + " " + win + " " + lose);
        }
    }

    static void input() throws IOException {
        isSelected = new boolean[9];
        player1 = new int[9];
        player2 = new int[9];
        win = 0;
        lose = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 9; i++)
            player1[i] = Integer.parseInt(st.nextToken());

        int idx = 0;
        for (int i = 1; i <= 18; i++) {
            boolean check = false;
            for (int j = 0; j < 9; j++) {
                if (player1[j] == i) {
                    check = true;
                    break;
                }
            }
            if (!check) { // player1에게 없는 경우 player2의 카드
                player2[idx++] = i;
            }
        }
    }

    static void play(int round, int p1, int p2) {
        if (round == 9) {
            if (p1 > p2) // 규영이가 이기는 경우
                win++;
            else if (p1 < p2) // 인영이가 이기는 경우
                lose++;
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (!isSelected[i]) {
                isSelected[i] = true;

                if (player1[round] > player2[i]) {
                    p1 += player1[round] + player2[i];
                    play(round + 1, p1, p2);
                    p1 -= player1[round] + player2[i];
                }
                
                else if (player1[round] < player2[i]) {
                    p2 += player1[round] + player2[i];
                    play(round + 1, p1, p2);
                    p2 -= player1[round] + player2[i];
                }

                isSelected[i] = false;
            }
        }
    }
}