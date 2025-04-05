import java.io.*;
import java.util.*;

public class Solution {
    static int T, N, M, K, A, B, time, ans;
    static Counter a[], b[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static PriorityQueue<Person> delayA = new PriorityQueue<>((o1, o2) -> {
        if (o1.ArriveTime != o2.ArriveTime)
            return o1.ArriveTime - o2.ArriveTime;
        return o1.memberIdx - o2.memberIdx;
    });
    static Queue<Person> processA = new LinkedList<>();
    static PriorityQueue<Person> delayB = new PriorityQueue<>((o1, o2) -> {
        if (o1.ArriveTime != o2.ArriveTime)
            return o1.ArriveTime - o2.ArriveTime;
        return o1.processIdx - o2.processIdx;
    });
    static Queue<Person> processB = new LinkedList<>();

    static Set<Integer> customerA = new HashSet<>(); // 고객번호(idx) 저장
    static Set<Integer> customerB = new HashSet<>();

    static class Counter {
        int time;
        boolean isUsing;

        Counter(int time, boolean isUsing) {
            this.time = time;
            this.isUsing = isUsing;
        }
    }

    static class Person {
        int memberIdx; // 고객번호
        int ArriveTime; // 도착 시간
        int processTime = 0; // 작업 시간
        int processIdx; // 창구 번호

        Person(int memberIdx, int ArriveTime, int processIdx) {
            this.memberIdx = memberIdx;
            this.ArriveTime = ArriveTime;
            this.processIdx = processIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            input(); // 입력
            start(); // 시뮬레이션 시작
            getSum(); // 정답 구하기

            sb.append("#").append(t).append(" ").append(ans > 0 ? ans : -1).append("\n");
        }
        System.out.println(sb);
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        a = new Counter[N + 1];
        b = new Counter[M + 1];

        ans = 0; // 초기화
        delayA.clear();
        delayB.clear();
        processA.clear();
        processB.clear();
        customerA.clear();
        customerB.clear();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int t = Integer.parseInt(st.nextToken());
            a[i] = new Counter(t, false);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int t = Integer.parseInt(st.nextToken());
            b[i] = new Counter(t, false);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= K; i++) {
            int t = Integer.parseInt(st.nextToken());
            delayA.add(new Person(i, t, -1));
        }
    }

    static void start() {
        time = 0; // 초기화

        while (true) {

            /* 1. 접수 창구 : 대기 -> 창구 배치 */
            waitA();

            /* 2. 접수 창구 : 작업 시간 감소 */
            doA();

            /* 3. 작업 창구 : 대기 -> 창구 배치 */
            waitB();

            /* 4. 작업 창구 : 작업 시간 감소 */
            doB();

            if (delayA.isEmpty() && delayB.isEmpty() && processA.isEmpty() && processB.isEmpty()) // 모든 작업이 끝나면 종료
                return;

            time++;
        }

    }

    static void waitA() {
        Iterator<Person> it = delayA.iterator();

        while (it.hasNext()) {
            Person p = it.next();

            if (p.ArriveTime <= time) { // 도착한 경우

                boolean isPossible = false; // 배치 가능 여부

                // 현재 배치 가능한 창구가 있는지 확인
                // 배치 가능한 창구가 여러곳인 경우 창구 인덱스가 작은 것부터 배치
                for (int j = 1; j < a.length; j++) {
                    if (!a[j].isUsing) { // 사용 가능한 창구인 경우 사용
                        a[j].isUsing = true;
                        p.processIdx = j;
                        p.processTime = a[j].time;
                        processA.add(p);

                        if (p.processIdx == A) // 지갑을 잃어버린 사람과 같은 창구 이용 시 정답 배열에 넣기
                            customerA.add(p.memberIdx);

                        it.remove(); // 현재 큐에서 삭제
                        isPossible = true; // 배치 가능
                        break; // 배치 끝나면 탐색 중지
                    }
                }
                if (!isPossible) { // 창구가 모두 차있는 경우
                    break;
                }
            } else { // 아직 도착하지 않은 경우
                break;
            }
        }
    }

    static void waitB() {
        Iterator<Person> it = delayB.iterator();

        while (it.hasNext()) {
            Person p = it.next();

            if (p.ArriveTime <= time) { // 도착한 경우

                boolean isPossible = false; // 배치 가능 여부

                // 현재 배치 가능한 창구가 있는지 확인
                // 배치 가능한 창구가 여러곳인 경우 창구 인덱스가 작은 것부터 배치
                for (int j = 1; j < b.length; j++) {
                    if (!b[j].isUsing) { // 사용 가능한 창구인 경우 사용
                        b[j].isUsing = true;
                        p.processIdx = j;
                        p.processTime = b[j].time;
                        processB.add(p);

                        if (p.processIdx == B) // 지갑을 잃어버린 사람과 같은 창구 이용 시 정답 배열에 넣기
                            customerB.add(p.memberIdx);

                        it.remove(); // 현재 큐에서 삭제
                        isPossible = true; // 배치 가능
                        break; // 배치 끝나면 탐색 중지
                    }
                }
                if (!isPossible) { // 창구가 모두 차있는 경우
                    break;
                }
            } else { // 아직 도착하지 않은 경우
                break;
            }
        }
    }

    static void doA() {
        Iterator<Person> it = processA.iterator();

        while (it.hasNext()) {
            Person p = it.next(); // 작업 중인 사람
            p.processTime--;

            if (p.processTime == 0) { // 작업이 끝난 경우
                a[p.processIdx].isUsing = false;
                p.ArriveTime = time; // 작업 창구에서의 선착순을 위해 도착 시간을 새로 저장
                delayB.add(p);
                it.remove();
            }
        }
    }

    static void doB() {
        Iterator<Person> it = processB.iterator();

        while (it.hasNext()) {
            Person p = it.next(); // 작업 중인 사람
            p.processTime--;

            if (p.processTime == 0) {// 작업이 끝난 경우
                b[p.processIdx].isUsing = false;
                it.remove();
            }
        }
    }

    static void getSum() {
        for (int member : customerA) {
            if (customerB.contains(member)) {
                ans += member;
            }
        }
    }
}