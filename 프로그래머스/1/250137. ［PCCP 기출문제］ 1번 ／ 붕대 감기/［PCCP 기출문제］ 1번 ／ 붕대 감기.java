class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int continuousTime = bandage[0];
        int plusHP = bandage[1];
        int bonusHP = bandage[2];
        
        int startTime = 1;
        int endTime = startTime+continuousTime-1;
        int HP =  health;
        
        for(int attack[]: attacks) {
            
            // 보너스HP 계산
            if (startTime < attack[0]) {
                int loop = (attack[0]-startTime)/continuousTime;
                
                startTime += loop*continuousTime;
                endTime += loop*continuousTime;
                
                HP += loop*continuousTime*plusHP;
                HP += loop*bonusHP;
            }
                       
            // 공격 전에 힐하고 있었던 경우
            if (endTime >= attack[0])
                HP += (attack[0]-startTime)*plusHP;
                
            // HP가 최대치를 넘기는 경우 보정
            if (HP > health) HP=health; 
            
            // 공격 받음
            HP -= attack[1];
            if (HP <= 0) return -1; // 체력이 0 되면 바로 게임 종료
            startTime = attack[0]+1; // 다음 힐 시작 시간
            endTime = startTime + continuousTime-1;
        }
        
        return HP;
    }

}