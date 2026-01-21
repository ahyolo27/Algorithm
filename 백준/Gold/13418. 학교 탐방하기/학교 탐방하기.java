import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, max, min;
    static List<Edge> edges = new ArrayList<>();

    static class Edge {
        int a, b, w;

        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        edges.sort(Comparator.comparingInt(o -> o.w));
        kruskal();
        edges.sort(Comparator.comparingInt(o -> -o.w));
        kruskal();

        System.out.println(max * max - min * min);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int line = Integer.parseInt(st.nextToken()); // 0: 오르막길, 1: 내리막길

            edges.add(new Edge(a, b, line));
        }

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
    }

    static void kruskal() {
        int parent[] = new int[N + 1];
        int rank[] = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int weight = 0;
        int cnt = 0;

        for (Edge e : edges) {
            if (find(e.a, parent) != find(e.b, parent)) {
                union(e.a, e.b, parent, rank);
                if (e.w == 0) // 오르막길
                    weight++;
                cnt++;
            }

            if (cnt == N) break;
        }

        max = Math.max(max, weight);
        min = Math.min(min, weight);
    }

    static int find(int a, int parent[]) { // 최상위 부모 찾기
        if (parent[a] != a)
            parent[a] = find(parent[a], parent);
        return parent[a];
    }

    static void union(int a, int b, int parent[], int rank[]) {
        int parentA = find(a, parent);
        int parentB = find(b, parent);

        if (parentA == parentB) return;

        if (rank[parentA] < rank[parentB])
            parent[parentA] = parentB;
        else if (rank[parentA] > rank[parentB])
            parent[parentB] = parentA;
        else {
            parent[parentB] = parentA;
            rank[parentA]++;
        }
    }
}