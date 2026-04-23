import java.util.Arrays;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n-lost.length;
        
        Arrays.sort(lost);
        Arrays.sort(reserve);
        
        for(int i=0;i<reserve.length;i++) {
            for(int j=0;j<lost.length;j++) {
                if (reserve[i]==lost[j]) {
                    reserve[i]=-1;
                    lost[j]=-1;
                    answer++;
                    break;
                }
            }
        }
        
        for(int r: reserve) {
            if (r==-1) continue;
            for(int i=0;i<lost.length;i++) {
                if (lost[i] == -1) continue;
                if (lost[i] == r-1 || lost[i] == r+1) {
                    lost[i] = -1;
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}