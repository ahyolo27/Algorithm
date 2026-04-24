import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> result = new ArrayList<>();
        int days[] = new int[progresses.length]; // 필요한 날
        
        for(int i=0;i<progresses.length;i++) 
            days[i] = (int) Math.ceil((double)(100 - progresses[i])/speeds[i]);
    
        int cnt = 0;
        int max = days[0];
        
        for(int i=0;i<days.length;i++) {
            if (max >= days[i]) {
                cnt++;
            } else {
                result.add(cnt);
                cnt = 1;
                max = days[i];
            }
        }
        result.add(cnt);
        
        int answer[] = new int[result.size()];
        for(int i=0;i<answer.length;i++)
            answer[i] = result.get(i);
        
        return answer;
    }
}