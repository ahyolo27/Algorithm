import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char letters[];
    static StringBuilder s = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        getCode(0, 0, new StringBuilder());

        System.out.println(s);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        letters = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++)
            letters[i] = st.nextToken().charAt(0);
        Arrays.sort(letters);
    }

    static void getCode(int depth, int start, StringBuilder sb) {
        if (depth == L) {
            if (check(sb)) // 조건에 충족하는 경우 정답 리스트에 추가
                s.append(sb).append("\n");
            return;
        }

        for (int i = start; i < letters.length; i++) {
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) < letters[i]) { // 조건2) 문자가 오름차순으로 배치
                sb.append(letters[i]);
                getCode(depth + 1, i + 1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    static boolean check(StringBuilder s) { // 조건1) 모음 1개 이상, 자음 2개 이상
        int cnt1 = 0; // 모음 개수
        int cnt2 = 0; // 자음 개수

        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    cnt1++;
                    break;
                default:
                    cnt2++;
            }
        }
        return cnt1 >= 1 && cnt2 >= 2;
    }
}