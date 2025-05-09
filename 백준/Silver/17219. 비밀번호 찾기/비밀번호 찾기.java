import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, String> password = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        search(); // 비밀번호 찾기

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String web = st.nextToken();
            String pw = st.nextToken();
            password.put(web, pw);
        }
    }

    static void search() throws IOException {
        for (int i = 0; i < M; i++) {
            String web = br.readLine();
            sb.append(password.get(web)).append("\n");
        }
    }
}