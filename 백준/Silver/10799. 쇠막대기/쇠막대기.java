import java.util.*;
import java.io.*;

public class Main {
    static int sum;
    static char[] MAP;
    static Stack<Stick> sticks = new Stack<>();
    static ArrayList<Integer> laysers = new ArrayList<>();


    static class Stick {
        char c;
        int idx;

        Stick(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력
        cutSticks(); // 막대 위치시키고 자르기

        System.out.println(sum);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        MAP = br.readLine().toCharArray();

        for (int i = 0; i < MAP.length - 1; i++) {
            if (MAP[i] == '(' && MAP[i + 1] == ')') { // 레이저인 경우
                MAP[i] = '-';
                MAP[i + 1] = '-';
                laysers.add(i);
            }
        }
    }

    static void cutSticks() {
        if (MAP[0] != '-')
            sticks.push(new Stick(MAP[0], 0));

        for (int i = 0; i < MAP.length; i++) {
            if (MAP[i] != '-') // 레이저가 아닌 경우
                sticks.push(new Stick(MAP[i], i));

            if (sticks.size() > 1) {
                Stick s1 = sticks.pop();
                Stick s2 = sticks.pop();

                if (!isStick(s1.c, s2.c)) { // 막대기가 아닌 경우 도로 넣기
                    sticks.push(s2);
                    sticks.push(s1);
                } else {// 막대기인 경우 자르기
                    int startIdx = s2.idx;
                    int endIdx = s1.idx;

                    sum++; // 자기 자신

                    for (int j = 0; j < laysers.size(); j++) {
                        if (startIdx < laysers.get(j) && laysers.get(j) < endIdx) // 레이저에 잘리면
                            sum++;
                        else if (laysers.get(j) > endIdx) // 레이저 구간 끝나면 종료
                            break;
                    }
                }
            }
        }
    }

    static boolean isStick(char a, char b) { // 막대기이면 true
        return a == ')' && b == '('; // 먼저 꺼낸 것이 a
    }
}