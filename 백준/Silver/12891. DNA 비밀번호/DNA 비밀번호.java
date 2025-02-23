import java.util.*;
import java.io.*;

public class Main {
    static int S, P, A, C, G, T, cnt;
    static String str;
    static int count[]; // 초기 윈도우

    public static void main(String[] args) throws IOException {
        input(); // 입력

        for (int i = 0; i < P; i++) // 초기 윈도우 설정
            add(str.charAt(i));
        if (check())
            cnt++;

        for (int i = P; i < S; i++) { // 슬라이딩 윈도우
            add(str.charAt(i));
            remove(str.charAt(i - P));
            if (check())
                cnt++;
        }

        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken()); // 문자열 길이
        P = Integer.parseInt(st.nextToken()); // 비밀번호 길이
        str = br.readLine(); // 문자열
        count = new int[4];

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
    }

    static void add(char l) {
        switch (l) {
            case 'A':
                count[0]++;
                break;
            case 'C':
                count[1]++;
                break;
            case 'G':
                count[2]++;
                break;
            case 'T':
                count[3]++;
                break;
        }
    }

    static void remove(char l) {
        switch (l) {
            case 'A':
                count[0]--;
                break;
            case 'C':
                count[1]--;
                break;
            case 'G':
                count[2]--;
                break;
            case 'T':
                count[3]--;
                break;
        }
    }

    static boolean check() {
        return count[0] >= A && count[1] >= C && count[2] >= G && count[3] >= T;
    }
}