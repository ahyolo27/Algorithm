import java.util.*;
import java.io.*;

public class Main {
    static int n, max;
    static int list[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        findMax(); // 수열의 최대 길이 찾기

        System.out.println(max + 1);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        list = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            list[i] = Integer.parseInt(st.nextToken());
    }

    static void findMax() {
        int idx = 0;

        //연속해서 커지는 경우
        while (idx < n - 1) {
            int cnt = 0;
            for (int i = idx; i < n - 1; i++) {
                if (list[i] <= list[i + 1])
                    cnt++;
                else
                    break;
            }
            max = Math.max(max, cnt);
            idx++;
        }

        //연속해서 작아지는 경우
        idx = 0;
        while (idx < n - 1) {
            int cnt = 0;
            for (int i = idx; i < n - 1; i++) {
                if (list[i] >= list[i + 1])
                    cnt++;
                else
                    break;
            }
            max = Math.max(max, cnt);
            idx++;
        }
    }
}