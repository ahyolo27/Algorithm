import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int answer = 0;
        int N = land.length;
        int M = land[0].length;

        int sum[] = new int[M]; // 행 별 시추 가능한 석유의 총량

        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

        Queue<Pos> oils = new LinkedList<>(); // 덩어리 bfs
        boolean visited[][] = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                
                if (land[i][j] == 1 && !visited[i][j]) { // 석유 덩어리 시작점을 발견한 경우
                    boolean isUsed[] = new boolean[M]; // 행 사용 여부를 확인하는 배열

                    visited[i][j] = true;
                    isUsed[j] = true;
                    int cnt = 1;
                    oils.add(new Pos(i, j));

                    // 시작점을 기준으로 덩어리 내 석유 카운트
                    while (!oils.isEmpty()) {
                        Pos now = oils.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextR = now.r + dr[k];
                            int nextC = now.c + dc[k];

                            if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) continue;

                            if (land[nextR][nextC] == 1 && !visited[nextR][nextC]) {
                                visited[nextR][nextC] = true;
                                isUsed[nextC] = true; // 걸친 행의 정보 저장
                                cnt++;
                                oils.add(new Pos(nextR, nextC));
                            }
                        }
                    }

                    // 걸친 행에 석유 값 저장
                    for (int k = 0; k < M; k++)
                        if (isUsed[k]) sum[k] += cnt;
                }
            }
        }

        for (int s : sum)
            answer = Math.max(answer, s);

        return answer;
    }

    class Pos {
        int r, c;

        Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}