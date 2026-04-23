import java.util.Map;
import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> nameList = new HashMap<>();

        for(String name: participant) {
            if (nameList.containsKey(name))
                nameList.put(name, nameList.get(name) + 1);
            else
                nameList.put(name, 1);
        }

        for(String name: completion)
            nameList.put(name, nameList.get(name) - 1);

        for(String name: nameList.keySet()) {
            if (nameList.get(name) > 0) {
                answer = name;
                break;
            }
        }

        return answer;
    }
}