import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int answer = Integer.MAX_VALUE;
        
        final int LIMIT = 151;

        int dp[][] = new int[LIMIT][LIMIT]; // dp[알고력][코딩력]=시간
        for(int d[]:dp)
            Arrays.fill(d, Integer.MAX_VALUE);
        dp[alp][cop] = 0; // init

        // solution        
        for(int a=alp;a<dp.length;a++) {
            for(int c=cop;c<dp[a].length;c++) {
                
                // 도달 불가능한 경우
                if (dp[a][c] == Integer.MAX_VALUE) continue;
                
                // 알고력이나 코딩력을 올리는 경우
                if (a < LIMIT-1)
                    dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c]+1);
                if (c < LIMIT-1)
                    dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c]+1);

                // 문제를 푸는 경우
                int cnt = 0;
                for(int problem[]: problems) {
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];
                    
                    // 풀 수 있는 경우 -> 풀이
                    if (a >= alp_req && c >= cop_req) {
                        int aIdx = (a+alp_rwd)>150?150:(a+alp_rwd);
                        int cIdx = (c+cop_rwd)>150?150:(c+cop_rwd);
                        
                        dp[aIdx][cIdx] = Math.min(dp[aIdx][cIdx], dp[a][c]+cost);
                        cnt++;
                    }
                }
                
                if (cnt == problems.length) // 문제를 다 풀 수 있는 경우
                    answer = Math.min(answer, dp[a][c]); // 갱신
            }
        }
        
        return answer;
    }
}