import java.io.*;
import java.util.*;

public class Main {
    static int N, population[], min, ans;
    static List<Integer> graph[];

    public static void main(String[] args) throws IOException {

        input(); // 입력
        for (int i = 1; i < N; i++) // 게리맨더링 시뮬레이션
            combination(i, 1, new ArrayList<>());

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE; // 초기화
        min = Integer.MAX_VALUE;

        population = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
            population[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
            }
        }
    }

    static void combination(int n, int depth, ArrayList<Integer> list) {
        if (depth == n + 1) {
            if (isConnected(list))
                ans = Math.min(min, ans);
            return;
        }

        for (int i = depth; i <= N; i++) {
            list.add(i);
            combination(n, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    static boolean isConnected(ArrayList<Integer> selected) {

        // 조합으로 선택한 도시들 간 연결 여부 확인
        boolean connection1[] = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(selected.get(0));
        connection1[selected.get(0)] = true;

        while (!q.isEmpty()) {
            int n = q.poll();
            for (int next : graph[n])
                if (!connection1[next] && selected.contains(next)) {
                    connection1[next] = true;
                    q.offer(next);
                }
        }

        // 조합에서 선택되지 않은 도시들 리스트 생성
        ArrayList<Integer> unselected = new ArrayList<>();
        for (int i = 1; i <= N; i++)
            if (!selected.contains(i))
                unselected.add(i);

        // 선택되지 않은 도시들 간 연결 여부 확인
        boolean connection2[] = new boolean[N + 1];
        q.offer(unselected.get(0));
        connection2[unselected.get(0)] = true;

        while (!q.isEmpty()) {
            int n = q.poll();
            for (int next : graph[n])
                if (!connection2[next] && unselected.contains(next)) {
                    connection2[next] = true;
                    q.offer(next);
                }
        }

        if (check(selected, connection1) && check(unselected, connection2)) { // 두 집단이 둘다 서로 연결되어 있으면 true
            int sum1 = 0;
            int sum2 = 0;
            for (int s : selected) sum1 += population[s];
            for (int us : unselected) sum2 += population[us];

            min = Math.abs(sum1 - sum2); // 인구 차이 저장
            return true;
        }
        return false;
    }

    static boolean check(ArrayList<Integer> selected, boolean isSelected1[]) { // 리스트의 모든 원소가 서로 연결되어 있으면 true
        for (int n : selected)
            if (!isSelected1[n])
                return false;
        return true;
    }
}