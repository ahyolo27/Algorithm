import java.util.*;

class Solution {
    int min = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        int maxMentor = n - k + 1; // 한 유형이 가질 수 있는 최대 멘토 수
        int remainMentors = n - k;

        int time[][] = new int[k + 1][maxMentor + 1]; // 유형-멘토수 별 누적 대기 시간

        for (int type = 1; type <= k; type++)
            time[type] = simulate(maxMentor, type, reqs);

        comb(1, 0, new int[k + 1], remainMentors, time);

        return min;
    }

    int[] simulate(int maxMentor, int type, int reqs[][]) {
        int sum[] = new int[maxMentor + 1];

        PriorityQueue<Integer> status = new PriorityQueue<>();

        for (int mentor = 1; mentor <= maxMentor; mentor++) {
            for (int i = 0; i < mentor; i++)
                status.add(0);

            for (int req[] : reqs) {
                if (type != req[2]) continue;

                int arrivedAt = req[0];
                int time = req[1];

                int now = status.peek();

                if (now <= arrivedAt) { // 이어서 상담할 수 있는 경우
                    status.poll();
                } else { // 대기가 있는 경우
                    sum[mentor] += now - arrivedAt; // 대기 시간 추가
                    status.poll();
                    arrivedAt = now; // 시작 시간 조정
                }

                status.add(arrivedAt + time);
            }
            status.clear();
        }

        return sum;
    }

    void comb(int depth, int cnt, int selected[], int remainMentors, int time[][]) {
        if (depth == selected.length) {
            if (cnt != remainMentors) return;

            int sum = 0;
            for (int num = 1; num < selected.length; num++) {
                sum += time[num][selected[num] + 1];
            }

            min = Math.min(sum, min);
            return;
        }

        if (cnt > remainMentors) return; // 이미 제한을 넘은 경우

        for (int i = 0; i <= remainMentors; i++) {
            selected[depth] = i;
            comb(depth + 1, cnt + i, selected, remainMentors, time);
        }
    }
}