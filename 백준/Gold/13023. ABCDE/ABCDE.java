import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean isFriend, isSelected[];
    static List<Integer> graph[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        start();

        System.out.println(isFriend ? 1 : 0);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        isSelected = new boolean[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    static void start() {
        for (int i = 0; i < N; i++) {
            if (isFriend) return; // 관계가 있다고 판단되면 더 탐색하지 않고 종료

            Arrays.fill(isSelected, false);
            isSelected[i] = true;
            dfs(0, i);
        }
    }

    static void dfs(int depth, int start) {
        if (depth == 4) {
            isFriend = true;
            return;
        }

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (!isSelected[next]) {
                isSelected[next] = true;
                dfs(depth + 1, next);
                isSelected[next] = false;
            }
        }
    }
}