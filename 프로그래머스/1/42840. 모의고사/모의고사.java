import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int type[][] = {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        int score[] = new int[3];
                
        // 채점
        for(int i=0;i<answers.length;i++) {
            for(int person=0;person<3;person++) {
                
            if (type[person][i%type[person].length] == answers[i])
                score[person]++;
            }
        }
        
        // 가장 높은 사람 계산
        ArrayList<Integer> result = new ArrayList<>();
        int maxScore = 0;
        
        for(int s: score)
            maxScore=Math.max(s, maxScore);
        
        for(int i=0;i<score.length;i++)
            if (score[i] == maxScore) result.add(i+1);
        
        // 정답 리턴
        int answer[] = new int[result.size()];
        for(int i=0;i<result.size();i++)
            answer[i] = result.get(i);
        
        return answer;
    }
}