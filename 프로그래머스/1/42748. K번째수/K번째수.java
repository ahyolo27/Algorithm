import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int command[]: commands) {
            
            int start = command[0] - 1;
            int end = command[1] - 1;
            int k = command[2] - 1;
            
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=start;i<=end;i++)
                list.add(array[i]);
            Collections.sort(list);
            answer.add(list.get(k));
        }
        
        int ans[] = new int[answer.size()];
        for(int i=0;i<answer.size();i++)
            ans[i]=answer.get(i);
        
        return ans;
    }
}