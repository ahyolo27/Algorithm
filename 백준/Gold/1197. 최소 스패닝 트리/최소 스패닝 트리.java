import java.util.*;
import java.io.*;

public class Main { // 크루스칼
    static int V, E, parents[], total;
    static List<Edge> edges = new ArrayList<>();

    static class Edge {
        int u, v, e;

        Edge(int u, int v, int e) {
            this.u = u;
            this.v = v;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        input(); // 입력
        getMST(); // MST 그리기
        System.out.println(total); // 가중치의 합 출력
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        for (int i = 1; i <= V; i++)
            parents[i] = i;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
    }

    static void getMST() {
        edges.sort(Comparator.comparingInt(o -> o.e)); // 가중치 기준 오름차순 정렬

        for (Edge edge : edges) {
            int pa = findParent(edge.u);
            int pb = findParent(edge.v);
            if (pa == pb) continue; // 부모가 같으면 continue

            unionParent(pa, pb); // 부모 합치기
            total += edge.e;
        }
    }

    static void unionParent(int a, int b) {
        if (a < b) // 낮은 쪽 기준으로 합치기
            parents[b] = a;
        else
            parents[a] = b;
    }

    static int findParent(int n) {
        if (n == parents[n])
            return n;
        else
            return parents[n] = findParent(parents[n]);
    }
}