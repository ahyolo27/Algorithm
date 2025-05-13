import java.io.*;

public class Main {
    static int N, M, ans;
    static String S;

    public static void main(String[] args) throws IOException {

        input(); // 입력
        kmp(); // 문자열 찾기

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();
    }

    static void kmp() { // KMP 알고리즘 사용
        int L = 2 * N + 1; // 패턴 길이
        char P[] = new char[L]; // 패턴 문자열
        for (int i = 0; i < L; i++)
            P[i] = (i % 2 == 0 ? 'I' : 'O');

        int pi[] = new int[L]; // prefix 함수(pi[]: 맨 뒤에서 겹치는 최대 접두/접미 문자열의 길이) 계산
        for (int i = 1, j = 0; i < L; i++) {
            while (j > 0 && P[i] != P[j])
                j = pi[j - 1];
            if (P[i] == P[j])
                pi[i] = ++j;
        }

        int j = 0;
        int cnt = 0;
        for (int i = 0; i < M; i++) { // KMP 검색
            while (j > 0 && S.charAt(i) != P[j]) // 일치하지 않는 경우 돌아갈 위치 결정
                j = pi[j - 1];
            if (S.charAt(i) == P[j]) { // 일치하는 경우 j 증가
                if (++j == L) { // 패턴 매칭 완료시 cnt 증가
                    cnt++;
                    j = pi[j - 1]; // 겹쳐도 되므로 돌아갈 위치 결정
                }
            }
        }

        ans = cnt;
    }
}