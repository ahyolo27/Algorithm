import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int[][] answer;

        ArrayList <int[]> a = new ArrayList<>();
        
        // 기본 설정
        HashMap <String, Integer> m = new HashMap<>();
        m.put("code", 0);
        m.put("date", 1);
        m.put("maximum", 2);
        m.put("remain", 3);
        
        // 1. 기준치보다 작은 데이터 뽑기
        for (int i = 0; i < data.length; i++) {
            if (data[i][m.get(ext)] < val_ext)
                a.add(data[i]);
        }
        answer = new int[a.size()][4];
        
        // 2. 기준에 따라 오름차순 정렬하기
        int idx = 0;
        while(true) {
            
            int min = 29991231;
            int tmp = 0;
            
            if (a.size() == 0)
                break;
            
            for (int i = 0; i < a.size(); i++) {
                if (a.get(i)[m.get(sort_by)] < min) {
                    min = a.get(i)[m.get(sort_by)];
                    tmp = i;
                }   
            }
            
            answer[idx] = a.get(tmp);
            idx++;
            a.remove(tmp);
        }
        
        return answer;
    }
}