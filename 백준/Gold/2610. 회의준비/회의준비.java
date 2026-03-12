import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, subset[], weight[][];
    static boolean graph[][];

    public static void main(String[] args) throws IOException {
        input(); // 입력

        makeSubset(); // 부분집합 만들기
        floyd(); // 플로이드 워셜로 거리 구하기

        print(); // 출력
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1]; // 관계
        subset = new int[N + 1]; // 속한 부분집합의 숫자

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = graph[b][a] = true;
        }
    }

    static void makeSubset() {
        for (int i = 1; i <= N; i++) {
            if (subset[i] == 0) {
                K++;
                dfs(i, K);
            }
        }
    }

    static void dfs(int now, int group) {
        subset[now] = group;

        for (int next = 1; next <= N; next++) {
            if (graph[now][next] && subset[next] == 0)
                dfs(next, group);
        }
    }

    static void floyd() {
        // init
        weight = new int[N + 1][N + 1];
        final int INF = 100_000_000;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) weight[i][j] = 0;
                else if (graph[i][j]) weight[i][j] = 1;
                else weight[i][j] = INF;
            }
        }

        // calc
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    weight[i][j] = Math.min(weight[i][j], weight[i][k] + weight[k][j]);
                }
            }
        }
    }

    static int getMax(int start) {
        int max = 0;
        for (int i = 1; i <= N; i++)
            if (subset[i] == subset[start]) // 연결되어 있는 경우
                max = Math.max(weight[start][i], max);

        return max;
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        sb.append(K).append("\n");

        int min, minIdx;
        int subsetIdx = 1;
        List<Integer> answers = new ArrayList<>();

        while (subsetIdx <= K) {
            min = Integer.MAX_VALUE;
            minIdx = -1;

            for (int i = 1; i <= N; i++) {
                if (subsetIdx != subset[i]) continue; // 다른 집합은 안 보기

                int max = getMax(i);

                if (min > max) {
                    min = max;
                    minIdx = i;
                }
            }

            answers.add(minIdx);
            subsetIdx++;
        }

        answers.sort(Comparator.comparingInt(o -> o)); // 오름차순 정렬
        for (int n : answers)
            sb.append(n).append("\n");

        System.out.println(sb);
    }
}