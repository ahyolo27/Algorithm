import java.util.*;
import java.io.*;

public class Main {
	static int N, map[][], WATER, max;
	static int dr[] = { -1, 0, 1, 0 }, dc[] = { 0, 1, 0, -1 };

	static class Pos {
		int r, c;

		Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {

		input(); // 입력
		find(); // 영역 찾기

		System.out.println(max);
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		WATER = -1; // 꽃가루 농도의 최댓값
		max = -1; // 반응X영역의 최대 개수

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				WATER = Math.max(map[i][j], WATER);
			}
		}
	}

	static void find() {
		for (int i = 0; i <= WATER; i++)
			bfs(i);
	}

	static void bfs(int water) {
		Queue<Pos> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {

				if (map[i][j] > water && !visited[i][j]) {
					q.add(new Pos(i, j)); // 탐색 시작점들을 큐에 넣기
					visited[i][j] = true;

					while (!q.isEmpty()) { // 인접 지점들 탐색
						Pos p = q.poll();

						for (int k = 0; k < 4; k++) {
							int nr = p.r + dr[k];
							int nc = p.c + dc[k];
							if (check(nr, nc) && !visited[nr][nc] && map[nr][nc] > water) {
								visited[nr][nc] = true;
								q.add(new Pos(nr, nc));
							}
						}
					}
					cnt++; // 영역 개수 추가
				}
			}
		}

		max = Math.max(cnt, max);
	}

	static boolean check(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}