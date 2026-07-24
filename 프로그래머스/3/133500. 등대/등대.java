import java.util.*;

class Solution {
    final int OFF = 0;
    final int ON = 1;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        List<Integer> map[] = new ArrayList[n+1]; // 등대 연결 맵
        for(int i=1;i<=n;i++)
            map[i] = new ArrayList<>();
        
        for(int l[]: lighthouse) { // 양방향 연결
            map[l[0]].add(l[1]);
            map[l[1]].add(l[0]);
        }
            
        int dp[][] = new int[n+1][2]; // dp[node][0:꺼짐/1:켜짐] = (선택한 등대 수);
        dfs(1, 0, map, dp);

        return Math.min(dp[1][OFF], dp[1][ON]);
    }
        
    void dfs(int now, int prev, List<Integer> map[], int dp[][]) {
        
        // 후위 순회       
        for(int next: map[now]) {
            if (next == prev) continue;
            
            dfs(next, now, map, dp);
        }
        
        // DP 구성
        for(int next: map[now]) {
            if (next == prev) continue; // 부모 방향(역방향) 제외

            dp[now][OFF] += dp[next][ON];
            dp[now][ON] += Math.min(dp[next][OFF], dp[next][ON]);
        }
        dp[now][ON]++;
    }
}