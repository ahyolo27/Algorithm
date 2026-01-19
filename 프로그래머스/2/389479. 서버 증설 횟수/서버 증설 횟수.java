import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Integer> servers = new LinkedList<>(); // integer -> remains

        for (int i = 0; i < players.length; i++) {
            int need = (int) Math.floor(players[i] / (double) m); // 현재 필요한 서버 수
            int nowCnt = servers.size(); // 현재 가동 중인 서버 수

            if (nowCnt < need) {
                for (int j = 0; j < need - nowCnt; j++)
                    servers.add(k);
                answer += (need - nowCnt); // 증설 횟수
            }

            nowCnt = servers.size();
            while (nowCnt > 0) {
                int remains = servers.poll();

                remains--;
                if (remains > 0)
                    servers.add(remains);

                nowCnt--;
            }
        }

        return answer;
    }
}