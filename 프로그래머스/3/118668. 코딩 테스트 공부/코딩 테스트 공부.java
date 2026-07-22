import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {              
        int maxAlp = 0;
        int maxCop = 0;
        for(int problem[]: problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }
        
        int dp[][] = new int[maxAlp+1][maxCop+1]; // dp[알고력][코딩력]=시간
        for(int d[]:dp)
            Arrays.fill(d, Integer.MAX_VALUE);

        // solution 
    
        if (alp >= maxAlp && cop >= maxCop) return 0; // 이미 짱인 경우
        
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        dp[alp][cop] = 0; // init
        
        for(int a=alp;a<=maxAlp;a++) {
            for(int c=cop;c<=maxCop;c++) {

                // 도달 불가능한 경우
                if (dp[a][c] == Integer.MAX_VALUE) continue;
                
                // 알고력이나 코딩력을 올리는 경우
                if (a < maxAlp)
                    dp[a+1][c] = Math.min(dp[a+1][c], dp[a][c]+1);
                if (c < maxCop)
                    dp[a][c+1] = Math.min(dp[a][c+1], dp[a][c]+1);

                // 문제를 푸는 경우
                for(int problem[]: problems) {
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];
                    
                    // 풀 수 있는 경우 -> 풀이
                    if (a >= alp_req && c >= cop_req) {
                        int aIdx = (a+alp_rwd)>maxAlp?maxAlp:(a+alp_rwd);
                        int cIdx = (c+cop_rwd)>maxCop?maxCop:(c+cop_rwd);
                        
                        dp[aIdx][cIdx] = Math.min(dp[aIdx][cIdx], dp[a][c]+cost);
                    }
                }
            }
        }
        
        return dp[maxAlp][maxCop];
    }
}