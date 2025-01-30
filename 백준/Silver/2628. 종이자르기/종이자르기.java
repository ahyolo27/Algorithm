import java.util.*;
import java.io.*;

public class Main {
    static int w, h, n, max; // 너비, 높이, 점선 수, 조각의 최대 크기
    static ArrayList<Integer> width = new ArrayList<>(); // 가로 점선
    static ArrayList<Integer> height = new ArrayList<>(); // 세로 점선

    public static void main(String[] args) throws IOException {

        input(); // 입력

        cut(); // 종이 자르기

        System.out.println(max);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 0) // 가로
                width.add(Integer.parseInt(st.nextToken()));
            else // 세로
                height.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void cut() {
        Collections.sort(width);
        Collections.sort(height);

        int tmpW[] = new int[width.size() + 1]; // 가로로 자른 조각 크기들을 담아둘 배열
        int tmpH[] = new int[height.size() + 1]; // 가로로 자른 조각 크기들을 담아둘 배열

        // 가로
        int current = 0;
        for (int i = 0; i < width.size(); i++) {
            tmpW[i] = (width.get(i) - current) * w;
            current = width.get(i);
        }
        tmpW[tmpW.length - 1] = (h - current) * w;

        // 세로
        current = 0;
        for (int i = 0; i < height.size(); i++) {
            tmpH[i] = height.get(i) - current;
            current = height.get(i);
        }
        tmpH[tmpH.length - 1] = w - current;

        // 계산
        for (int i = 0; i < tmpW.length; i++) {
            double tmp[] = new double[height.size() + 1];
            for (int j = 0; j < tmpH.length; j++) {
                tmp[j] = tmpW[i] * ((double) tmpH[j] / w);
                max = Math.max(max, (int) tmp[j]);
            }
        }
    }
}