import java.util.*;

class Solution {
    final int INF = Integer.MAX_VALUE;
 
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        // init
        int weight[][] = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            Arrays.fill(weight[i], INF);
            weight[i][i] = 0;
        }
        
        // setting floyd
        for(int r[]: road) {
            weight[r[0]][r[1]] = Math.min(weight[r[0]][r[1]], r[2]);
            weight[r[1]][r[0]] = Math.min(weight[r[1]][r[0]], r[2]);
        }
        
        // floyd
        floyd(N, weight);
        
        // calc
        for(int i=1;i<=N;i++)
            if (weight[1][i]<=K) answer++;

        return answer;
    }
    
    void floyd(int N, int weight[][]) {
        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    if (weight[i][k]!=INF && weight[k][j]!=INF)
                        weight[i][j] = Math.min(weight[i][j], weight[i][k]+weight[k][j]);
                }
            }
        }
    }
}