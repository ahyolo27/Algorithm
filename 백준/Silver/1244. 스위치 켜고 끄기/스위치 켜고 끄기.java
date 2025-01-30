import java.util.*;
import java.io.*;

public class Main {
    static int n, student;
    static int lights[];
    static Queue<Student> q = new LinkedList<>();

    static class Student {
        int gender, number;

        Student(int gender, int number) {
            this.gender = gender;
            this.number = number;
        }
    }

    public static void main(String[] args) throws IOException {

        input(); // 입력

        start(); // 스위치 켜고 끄기

        for (int i = 1; i < n + 1; i++)
            if (i % 20 == 0)
                System.out.print(lights[i] + "\n");
            else
                System.out.print(lights[i] + " ");
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 스위치 개수
        lights = new int[n + 1];

        // 스위치 세팅
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++)
            lights[i] = Integer.parseInt(st.nextToken());

        // 학생 세팅
        student = Integer.parseInt(br.readLine());
        for (int i = 0; i < student; i++) {
            st = new StringTokenizer(br.readLine());
            q.offer(new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    static void start() {
        while (!q.isEmpty()) {
            Student s = q.poll();

            if (s.gender == 1) // 남학생
                boy(s.number);
            else // 여학생
                girl(s.number);
        }
    }

    static void boy(int num) {
        for (int i = 1; i < n + 1; i++)
            if (i % num == 0) // 배수인 경우
                lights[i] = (lights[i] + 1) % 2;
    }

    static void girl(int num) {
        lights[num] = (lights[num] + 1) % 2; // 자기 자신 변경

        // 범위 내 값이고 좌우 대칭인 경우 변경
        for (int i = 1; i <= n / 2; i++) {
            if (check(num - i, num + i) && lights[num - i] == lights[num + i]) {
                lights[num - i] = (lights[num - i] + 1) % 2;
                lights[num + i] = (lights[num + i] + 1) % 2;
            } else // 그렇지 않은 경우 중지
                break;
        }
    }

    static boolean check(int n1, int n2) {
        return 1 <= n1 && n1 <= n && 1 <= n2 && n2 <= n;
    }
}