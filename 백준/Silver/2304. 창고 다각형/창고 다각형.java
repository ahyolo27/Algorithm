import java.util.*;
import java.io.*;

public class Main {
    static int n, sum;
    static ArrayList<Pos> list = new ArrayList<>();

    static class Pos {
        int l, h;

        Pos(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        calc(); // 계산

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // Pos.l값에 따라 list 정렬
        list.sort(new Comparator<Pos>() {
            public int compare(Pos a, Pos b) {
                return a.l - b.l;
            }
        });
    }

    static void calc() {
        // 최댓값 찾기
        int max = 0;
        int maxIdx = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).h > max) {
                max = list.get(i).h;
                maxIdx = i;
            }
        }
        sum += max;

        // 최댓값 기준 앞 구간
        int tmpMax = list.get(0).h;
        int tmpIdx = list.get(0).l;
        for (int i = 1; i <= maxIdx; i++) {
            if (tmpMax <= list.get(i).h) {
                sum += tmpMax * (list.get(i).l - tmpIdx);
                tmpIdx = list.get(i).l;
                tmpMax = list.get(i).h;
            }
        }

        // 최댓값 기준 뒷 구간
        tmpMax = list.get(list.size() - 1).h;
        tmpIdx = list.get(list.size() - 1).l;
        for (int i = list.size() - 1; i >= maxIdx; i--) {
            if (tmpMax <= list.get(i).h) {
                sum += tmpMax * (tmpIdx - list.get(i).l);
                tmpIdx = list.get(i).l;
                tmpMax = list.get(i).h;
            }
        }
    }
}