import java.util.*;
import java.io.*;

public class Main {
    static int N, map[][], ans;
    static ArrayList<Student> arr = new ArrayList<>();
    static int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};

    static class Student {
        int num, r, c;
        ArrayList<Integer> list;

        Student(int num, int r, int c, ArrayList<Integer> list) {
            this.num = num;
            this.list = list;
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        set(); // 자리 세팅
        calc(); // 만족도 계산

        System.out.println(ans);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = 0;

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());

            ArrayList<Integer> list = new ArrayList<>();
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));
            list.add(Integer.parseInt(st.nextToken()));

            arr.add(new Student(num, 0, 0, list));
        }
    }

    static void set() {
        for (int i = 0; i < arr.size(); i++) {
            int weight1[][] = new int[N][N]; // 좋아하는 학생의 수를 누적하는 배열


            // 조건 1) 좋아하는 학생 위치 파악하기
            int max = 0;
            int maxR = 0;
            int maxC = 0;
            boolean isUsed = false;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] == 0) { // 빈 자리이면
                        for (int t = 0; t < 4; t++) { // 사방에 좋아하는 학생이 있으면 +1
                            int nr = r + dr[t];
                            int nc = c + dc[t];
                            if (check(nr, nc) && arr.get(i).list.contains(map[nr][nc]))
                                weight1[r][c]++;
                        }
                        if (max < weight1[r][c]) {
                            max = weight1[r][c];
                            maxR = r;
                            maxC = c;
                        } else if (max == 0 && weight1[r][c] == 0 && !isUsed) {
                            maxR = r;
                            maxC = c;
                            isUsed = true;
                        }
                    }
                }
            }

            // 조건 2) 앞선 후보 중 비어있는 칸이 많은 곳 파악하기
            int cnt = 0;
            int weight2[][] = new int[N][N]; // 비어있는 칸을 누적하는 배열
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (weight1[r][c] == max && map[r][c] == 0) { // 좋아하는 학생 수가 최대이고 빈 자리이면
                        for (int t = 0; t < 4; t++) { // 빈 공간 개수 확인
                            int nr = r + dr[t];
                            int nc = c + dc[t];
                            if (check(nr, nc) && map[nr][nc] == 0)
                                weight2[r][c]++;
                        }
                        if (cnt < weight2[r][c]) {
                            cnt = weight2[r][c];
                            maxR = r;
                            maxC = c;
                        }
                    }
                }
            }

            map[maxR][maxC] = arr.get(i).num; // 자리 배치
            arr.get(i).r = maxR;
            arr.get(i).c = maxC;
        }
    }

    static void calc() {
        for (int i = 0; i < arr.size(); i++) {
            int score = 0;
            int r = arr.get(i).r;
            int c = arr.get(i).c;

            for (int t = 0; t < 4; t++) { // 빈 공간 개수 확인
                int nr = r + dr[t];
                int nc = c + dc[t];
                if (check(nr, nc) && arr.get(i).list.contains(map[nr][nc]))
                    score++;
            }

            if (score == 0)
                ans += 0;
            else if (score == 1)
                ans += 1;
            else if (score == 2)
                ans += 10;
            else if (score == 3)
                ans += 100;
            else
                ans += 1000;
        }
    }

    static boolean check(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}