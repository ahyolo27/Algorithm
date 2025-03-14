import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static HashMap<Integer, String> pokemonI2S = new HashMap<>();
    static HashMap<String, Integer> pokemonS2I = new HashMap<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        input(); // 입력
        find(); // 포켓몬 찾기

        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            pokemonI2S.put(i, name);
            pokemonS2I.put(name, i);
        }
    }

    static void find() throws IOException {
        for (int i = 0; i < M; i++) {
            String str = br.readLine();

            if (48 <= str.charAt(0) && str.charAt(0) <= 57) // 숫자인 경우
                sb.append(pokemonI2S.get(Integer.parseInt(str))).append("\n");
            else
                sb.append(pokemonS2I.get(str)).append("\n");
        }
    }
}