import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int lastGift[][] = new int[friends.length][friends.length]; // 주고 받은 기록 
        int giftRate[] = new int[friends.length]; // 선물 지수
        int nextGift[] = new int[friends.length]; // 다음 선물 개수
        
        // 0. 사람 저장
        HashMap<String, Integer> name = new HashMap<String, Integer>();
        for (int i = 0; i < friends.length; i++)
            name.put(friends[i], i);
        
        // 1. 주고 받은 기록 표 작성
        for (int i = 0; i < gifts.length; i++) {
            String s[] = gifts[i].split(" ");
            lastGift[name.get(s[0])][name.get(s[1])]++;
        }
        
        // 2. 선물 지수 계산
        for (int i = 0; i < friends.length; i++) {
            int a = 0;
            int b = 0;
            for (int j = 0; j < friends.length; j++){
                // 준 것
                a += lastGift[name.get(friends[i])][name.get(friends[j])];
                // 받은 것
                b += lastGift[name.get(friends[j])][name.get(friends[i])];            
            }
            giftRate[i] = a - b;
        }
        
        // 3. 경우의 수 계산
        for (int i = 0; i < friends.length; i++) {
            int c;
            int d;
            for (int j = i + 1; j < friends.length; j++) {
                // 주고 받은 적이 없거나 주고 받은 수가 같은 경우
                if (lastGift[name.get(friends[i])][name.get(friends[j])]
                    == lastGift[name.get(friends[j])][name.get(friends[i])]) {
                    if (giftRate[name.get(friends[i])] < giftRate[name.get(friends[j])])
                        nextGift[name.get(friends[j])]++;
                    else if (giftRate[name.get(friends[i])] > giftRate[name.get(friends[j])])
                        nextGift[name.get(friends[i])]++;
                }
                else { // 주고 받은 적이 있는 경우
                    if (lastGift[name.get(friends[i])][name.get(friends[j])]
                    > lastGift[name.get(friends[j])][name.get(friends[i])])
                        nextGift[name.get(friends[i])]++;
                    else
                        nextGift[name.get(friends[j])]++;
                }
            }
        }
        
        // 4. 가장 선물을 많이 받는 사람 찾기
        for (int i = 0; i < nextGift.length; i++) {
            answer = answer < nextGift[i] ? nextGift[i] : answer;
        }
        
        return answer;
    }
}