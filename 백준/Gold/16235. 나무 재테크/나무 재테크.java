import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K, map[][], foodValue[][];
    static ArrayList<Integer> trees[][];
    static int dr[] = {-1, -1, -1, 0, 1, 1, 1, 0}, dc[] = {-1, 0, 1, 1, 1, 0, -1, -1}; // 8방 탐색

    public static void main(String[] args) throws IOException {
        input(); // 입력
        while (K > 0)
            simulate();
        calc();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // N*N map
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1]; // 맵 내 양분 정보
        foodValue = new int[N + 1][N + 1]; // 겨울에 얻을 양분 정보
        trees = new ArrayList[N + 1][N + 1]; // 좌표 별 나무 정보

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++)
                trees[i][j] = new ArrayList<>(); // 양분 정보 초기화
            Arrays.fill(map[i], 5); // 맵 초기 설정
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++)
                foodValue[i][j] = Integer.parseInt(st.nextToken()); // 양분 정보 저장
        }

        int x, y, z;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());
            trees[x][y].add(z);
        }
    }

    static void simulate() {
        K--;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (trees[i][j].isEmpty()) continue; // 나무가 없다면 지나가기

                int stopIdx = trees[i][j].size();

                // spring
                trees[i][j].sort(Comparator.comparingInt(o -> o)); // 나무를 오름차순으로 정렬
                for (int k = 0; k < trees[i][j].size(); k++) {
                    int tree = trees[i][j].get(k);
                    if (map[i][j] >= tree) { // 양분이 충분한 경우
                        map[i][j] -= tree;
                        trees[i][j].set(k, tree + 1);
                    } else {
                        stopIdx = k;
                        break;
                    }
                }

                // summer
                for (int k = trees[i][j].size() - 1; k >= stopIdx; k--) {
                    map[i][j] += trees[i][j].get(k) / 2;
                    trees[i][j].remove(k);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                
                // fall
                for (int tree : trees[i][j]) {
                    if (tree % 5 == 0) {
                        for (int k = 0; k < 8; k++) {
                            int nr = i + dr[k];
                            int nc = j + dc[k];
                            if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
                            trees[nr][nc].add(1);
                        }
                    }
                }

                // winter
                map[i][j] += foodValue[i][j];
            }
        }
    }

    static void calc() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cnt += trees[i][j].size();
            }
        }
        System.out.println(cnt);
    }
}