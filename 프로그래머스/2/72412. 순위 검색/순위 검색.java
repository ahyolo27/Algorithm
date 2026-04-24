import java.util.*;

class Solution {
    public int[] solution(String[] infos, String[] query) {
        Map<String, ArrayList<Integer>> members = new HashMap<>(); 
        ArrayList<Integer> result = new ArrayList<>();
        
        // 1. 정보 넣기
        for(String info: infos) {
            String split[] = info.split(" ");
            int score = Integer.parseInt(split[4]);
            makeKey(0, "", split, members);
        }
        
        // 1-1. 전처리 - 점수 순 정렬
        for (ArrayList<Integer> list : members.values()) {
           Collections.sort(list);
        }

        // 2. 정보 검색하기
        for(String q: query) {
            int cnt = 0; // 조건을 충족하는 사람 수
            
            String split[] = q.split(" ");
            String key = split[0]+" "+split[2]+" "+split[4]+" "+split[6]+" ";
            int score = Integer.parseInt(split[7]);
            
            ArrayList<Integer> scores = members.get(key);
            if (scores == null) {
                result.add(0);
                continue;
            }
            
            int left = 0, right = scores.size();
            while(left<right) {
                int mid = (left+right)/2;
                if (scores.get(mid) < score)
                    left = mid+1;
                else
                    right = mid;
            }
            result.add(scores.size()-left);
        }
        
        int answer[] = new int[result.size()];
        for(int i=0;i<result.size();i++)
            answer[i] = result.get(i);
        
        return answer;
    }
    
    void makeKey(int depth, String key, String info[], Map<String, ArrayList<Integer>> members) {
        if (depth == 4) {
            members.putIfAbsent(key, new ArrayList<>());
            members.get(key).add(Integer.parseInt(info[4]));
            return;
        }
        
        makeKey(depth+1, key+info[depth]+" ", info, members);
        makeKey(depth+1, key+"- ", info, members);
    }
}