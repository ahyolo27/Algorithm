import java.io.*;
import java.util.*;

public class Main {
	static int N, M, cnt;
	static List<Integer> graph[];

	public static void main(String[] args) throws IOException {

		input(); // 입력
		calc(1); // 시작

		System.out.println(cnt);
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			graph[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}
	}

	static void calc(int start) {
		boolean visited[] = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		cnt = 0;

		// 나
		int node = q.poll();
		for (int i = 0; i < graph[node].size(); i++) {
			int next = graph[node].get(i);
			if (!visited[next]) {
				cnt++;
				visited[next] = true;
				q.add(next);
			}
		}
		
		// 친구들
		while(!q.isEmpty()) {
			node = q.poll();
			for (int i = 0; i < graph[node].size(); i++) {
				int next = graph[node].get(i);
				if (!visited[next]) {
					cnt++;
					visited[next] = true;
				}
			}
		}
	}

}
