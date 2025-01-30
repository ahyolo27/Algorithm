import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int A[][];
    static int B[][];
    static char answer[];


    public static void main(String[] args) throws IOException {

        input(); // 입력

        play(); // 승패 계산

        for (int i = 0; i < n; i++)
            System.out.println(answer[i]);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        A = new int[n][5];
        B = new int[n][5];
        answer = new char[n];
        int mark;

        for (int i = 0; i < n; i++) {
            // A 입력
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < a; j++) {
                mark = Integer.parseInt(st.nextToken());
                A[i][mark]++;
            }
            // B 입력
            st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            for (int j = 0; j < b; j++) {
                mark = Integer.parseInt(st.nextToken());
                B[i][mark]++;
            }
        }
    }

    static void play() {
        for (int i = 0; i < n; i++) {
            if (A[i][4] > B[i][4])
                answer[i] = 'A';
            else if (A[i][4] < B[i][4])
                answer[i] = 'B';
            else { // 별: 무승부
                if (A[i][3] > B[i][3])
                    answer[i] = 'A';
                else if (A[i][3] < B[i][3])
                    answer[i] = 'B';
                else { // 동그라미: 무승부
                    if (A[i][2] > B[i][2])
                        answer[i] = 'A';
                    else if (A[i][2] < B[i][2])
                        answer[i] = 'B';
                    else { // 네모: 무승부
                        if (A[i][1] > B[i][1])
                            answer[i] = 'A';
                        else if (A[i][1] < B[i][1])
                            answer[i] = 'B';
                        else
                            answer[i] = 'D';
                    }
                }
            }
        }
    }
}