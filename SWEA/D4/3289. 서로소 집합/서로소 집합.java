import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int T, N, M, parent[], rank[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parent = new int[N + 1];
            rank = new int[N + 1];

            makeSet(); // 배열 초기화

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 각 트리의 root 노드 구하기
                int rootA = find(a);
                int rootB = find(b);

                if (op == 0) { // : 합치기
                    if (a != b)
                        union(rootA, rootB);
                } else { // : 같은 집합인지 확인 -> 같은 집합이면 1, 아니면 0
                    if (rootA == rootB)
                        sb.append(1);
                    else
                        sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void makeSet() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    static int find(int a) { // a가 속한 트리의 root 반환
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int rootA, int rootB) { // 트리 합치기
        if (rootA == rootB) return; // 같은 트리에 속한 경우 그냥 종료

        if (rank[rootA] < rank[rootB])
            parent[rootA] = rootB;
        else {
            parent[rootB] = rootA;
            if (rank[rootA] == rank[rootB])  // rank가 같은 경우
                rank[rootA]++;
        }
    }
}