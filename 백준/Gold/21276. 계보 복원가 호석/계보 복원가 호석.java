import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, degree[];
    static String name[];
    static Map<String, Integer> idx;
    static List<Integer> graph[];
    static List<String> roots;
    static List<Integer> children[];

    public static void main(String[] args) throws IOException {
        input();

        sort(); // 위상정렬로 시조와 부모-자식 관계 정리

        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        idx = new HashMap<>();
        degree = new int[N];
        name = new String[N];
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            name[i] = st.nextToken();
        Arrays.sort(name); // 이름 정렬 후 인덱스 부여

        for (int i = 0; i < N; i++)
            idx.put(name[i], i);

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String next = st.nextToken();
            String prev = st.nextToken();
            graph[idx.get(prev)].add(idx.get(next)); // 그래프에 간선 추가: prev -> next
            degree[idx.get(next)]++; // 진입차수 증가
        }
    }

    static void sort() {
        Queue<Integer> q = new LinkedList<>();

        roots = new ArrayList<>(); // 시조
        children = new ArrayList[N]; // 부모-자식 트리
        for (int i = 0; i < N; i++)
            children[i] = new ArrayList<>();

        for (String name : idx.keySet()) {
            if (degree[idx.get(name)] == 0) {
                roots.add(name);
                q.add(idx.get(name));
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph[now]) {
                degree[next]--;

                if (degree[next] == 0) {
                    q.add(next);
                    children[now].add(next);
                }
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();

        sb.append(roots.size()).append("\n");
        Collections.sort(roots);
        for (String name : roots)
            sb.append(name).append(" ");
        sb.append("\n");

        for (int i = 0; i < N; i++) {
            sb.append(name[i]).append(" ");
            sb.append(children[i].size()).append(" ");

            if (!children[i].isEmpty()) {
                for (int childIdx : children[i])
                    sb.append(name[childIdx]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}