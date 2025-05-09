import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, Integer> name = new TreeMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find(); // 명단 찾기

        System.out.println(sb);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String n = br.readLine();
            name.put(n, 1);
        }

        for (int i = 0; i < M; i++) {
            String n = br.readLine();
            if (name.containsKey(n)) {
                int value = name.get(n) + 1;
                name.put(n, value);
            } else {
                name.put(n, 1);
            }
        }
    }

    static void find() {
        int cnt = 0;

        StringBuilder sb2 = new StringBuilder();
        for(String key: name.keySet()) {
            if (name.get(key) == 2) {
                cnt++;
                sb2.append(key).append("\n");
            }
        }

        sb.append(cnt).append("\n").append(sb2);
    }
}