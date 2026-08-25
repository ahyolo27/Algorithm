import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> result = new TreeMap<>(); // key=차번호, value=누적주차시간
        
        Map<String, String> cars = new HashMap<>(); // key=차번호, value=입차시간
        
        // 1. 입차-출차 쌍 체크
        for(String record: records){
            String info[] = record.split(" ");
            
            if (info[2].equals("IN")) {
                cars.put(info[1], info[0]);
            } else {
                String startTime = cars.get(info[1]);
                int calcTime = calcTime(startTime, info[0]);
                result.put(info[1], result.getOrDefault(info[1], 0) + calcTime);
                cars.remove(info[1]);
            }
        }
        
        // 2. 출차 안 한 차 체크
        for(String carNum: cars.keySet()) {
            String startTime = cars.get(carNum);
            int calcTime = calcTime(startTime, "23:59");
            result.put(carNum, result.getOrDefault(carNum, 0) + calcTime);   
        }

        // 3. 요금 계산
        int[] answer = new int[result.size()];
        int idx = 0;
        
        for(int totalTime:result.values()) {
            int totalFee = fees[1];
            if (totalTime > fees[0])
                totalFee += Math.ceil((double)(totalTime-fees[0])/fees[2])*fees[3];
            
            answer[idx++] = totalFee;
        }
        
        return answer;
    }
    
    int calcTime(String start, String end) {
        String startTime[] = start.split(":");
        String endTime[] = end.split(":");
        
        int hour = Integer.parseInt(endTime[0])-Integer.parseInt(startTime[0]);
        int min = Integer.parseInt(endTime[1])-Integer.parseInt(startTime[1]);
        if (min<0) {
            min = 60+min;
            hour-=1;
        }

        return hour*60 + min;
    } 
}