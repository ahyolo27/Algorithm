import java.io.*;
import java.util.*;

public class Main {
    static int N, E, map[][], cnt;
    static boolean visited[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        setVirus(1);

        System.out.println(cnt);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = 1;
        }
    }

    static void setVirus(int start) {
        visited[start] = true;

        for (int i = 1; i <= N; i++) {
            if (map[start][i] == 1 && !visited[i]) {
                setVirus(i);
                cnt++;
                visited[i] = true;
            }
        }
    }
}