import java.util.*;

class Solution {
    int max = 0;
    
    public int solution(int sticker[]) {
        int answer = 0;
        
        if(sticker.length == 1) 
            return sticker[0];
        else if (sticker.length < 3)
            return Math.max(sticker[0], sticker[1]);
        
        int dp[] = new int[sticker.length];
        
        // 첫번째 스티커를 선택하는 경우
        dp[0] = sticker[0];
        for(int i=1;i<dp.length-1;i++) {
            if(i==1) dp[i] = dp[i-1];
            else
                dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        }
        answer = dp[sticker.length-2];
        
        Arrays.fill(dp, 0); // 초기화

        // 첫번째 스티커를 선택하지 않는 경우
        dp[1] = sticker[1];
        for(int i=2;i<dp.length;i++)
            dp[i] = Math.max(dp[i-2]+sticker[i], dp[i-1]);
        answer = Math.max(answer, dp[sticker.length-1]);
        
        return answer;
    }
}