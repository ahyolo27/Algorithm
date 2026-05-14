import java.util.*;

class Solution {
    public String[] solution(String[] commands) {
        String map[][] = new String[51][51];
        for (String m[] : map)
            Arrays.fill(m, "");

        ArrayList<String> result = new ArrayList<>();

        Map<String, Integer> posToGroup = new HashMap<>();
        Map<Integer, ArrayList<String>> groupToPos = new HashMap<>();

        int groupIdx = 0;
        int r1, c1, r2, c2;
        String val1, val2;

        for (String command : commands) {
            String s[] = command.split(" ");

            switch (s[0]) {
                case "UPDATE":
                    if (s.length == 4) { /// UPDATE r c value
                        r1 = Integer.parseInt(s[1]);
                        c1 = Integer.parseInt(s[2]);
                        val1 = s[3];

                        if (posToGroup.containsKey(r1 + " " + c1)) // 병합 셀인 경우
                            updateGroup(posToGroup.get(r1 + " " + c1), val1, groupToPos, map);
                        else  // 단일 셀인 경우
                            map[r1][c1] = val1;

                    } else { /// UPDATE value1 value2
                        Set<Integer> updatedGroups = new HashSet<>();

                        val1 = s[1]; // 선택할 셀의 값
                        val2 = s[2]; // 바꿀 값

                        for (int i = 1; i <= 50; i++) {
                            for (int j = 1; j <= 50; j++) {
                                if (map[i][j].equals(val1)) {
                                    if (posToGroup.containsKey(i + " " + j)) { // 병합 셀인 경우
                                        int groupNo = posToGroup.get(i + " " + j);
                                        if (!updatedGroups.contains(groupNo)) { // 중복 처리 방지
                                            updateGroup(groupNo, val2, groupToPos, map);
                                            updatedGroups.add(groupNo);
                                        }
                                    } else // 단일 셀인 경우
                                        map[i][j] = val2;
                                }
                            }
                        }
                    }
                    break;
                case "MERGE":
                    ///  MERGE r1 c1 r2 c2
                    r1 = Integer.parseInt(s[1]);
                    c1 = Integer.parseInt(s[2]);
                    r2 = Integer.parseInt(s[3]);
                    c2 = Integer.parseInt(s[4]);
                    if (r1 == r2 && c1 == c2) continue; // 같은 셀 무시

                    val1 = map[r1][c1].isEmpty() ? map[r2][c2] : map[r1][c1];

                    if (!posToGroup.containsKey(r1 + " " + c1) && !posToGroup.containsKey(r2 + " " + c2)) { // 둘다 단일 셀인 경우
                        // 추가
                        posToGroup.put(r1 + " " + c1, groupIdx);
                        posToGroup.put(r2 + " " + c2, groupIdx);
                        groupToPos.put(groupIdx, new ArrayList<>());
                        groupToPos.get(groupIdx).add(r1 + " " + c1);
                        groupToPos.get(groupIdx).add(r2 + " " + c2);
                        // 갱신
                        updateGroup(groupIdx, val1, groupToPos, map);
                        groupIdx++;
                    } else if (posToGroup.containsKey(r1 + " " + c1) && !posToGroup.containsKey(r2 + " " + c2)) { // 앞만 병합셀
                        // 추가
                        int groupNo = posToGroup.get(r1 + " " + c1);
                        posToGroup.put(r2 + " " + c2, groupNo);
                        groupToPos.get(groupNo).add(r2 + " " + c2);
                        // 갱신
                        updateGroup(groupNo, val1, groupToPos, map);
                    } else if (!posToGroup.containsKey(r1 + " " + c1) && posToGroup.containsKey(r2 + " " + c2)) { // 뒤만 병합셀
                        // 추가
                        int groupNo = posToGroup.get(r2 + " " + c2);
                        posToGroup.put(r1 + " " + c1, groupNo);
                        groupToPos.get(groupNo).add(r1 + " " + c1);
                        // 갱신
                        updateGroup(groupNo, val1, groupToPos, map);
                    } else { // 둘다 병합 셀인 경우
                        int groupNo = posToGroup.get(r2 + " " + c2);
                        int newGroupNo = posToGroup.get(r1 + " " + c1);

                        // 이미 같은 병합 내 셀인 경우
                        if (groupNo == newGroupNo) continue;

                        // 추가
                        for (String pos : groupToPos.get(groupNo)) {
                            groupToPos.get(newGroupNo).add(pos);
                            posToGroup.put(pos, newGroupNo);
                        }
                        groupToPos.remove(groupNo);
                        // 갱신
                        updateGroup(newGroupNo, val1, groupToPos, map);
                    }
                    break;
                case "UNMERGE":
                    r1 = Integer.parseInt(s[1]);
                    c1 = Integer.parseInt(s[2]);
                    val1 = map[r1][c1];

                    // 병합 셀이 아닌 경우 스킵
                    if (!posToGroup.containsKey(r1 + " " + c1)) continue;

                    // 병합 내 셀 초기화
                    int groupNo = posToGroup.get(r1 + " " + c1);
                    updateGroup(groupNo, "", groupToPos, map);

                    // 병합 관계 삭제
                    for (String pos : groupToPos.get(groupNo))
                        posToGroup.remove(pos);
                    groupToPos.remove(groupNo);

                    // 중심 셀 값 삽입
                    map[r1][c1] = val1;
                    break;
                case "PRINT":
                    r1 = Integer.parseInt(s[1]);
                    c1 = Integer.parseInt(s[2]);
                    result.add(map[r1][c1].isEmpty() ? "EMPTY" : map[r1][c1]);
            }
        }

        return result.toArray(new String[0]);
    }

    void updateGroup(int groupNo, String val, Map<Integer, ArrayList<String>> groupToPos, String map[][]) {
        for (String pos : groupToPos.get(groupNo)) { // 병합 내 모든 셀의 값 변경
            String p[] = pos.split(" ");
            map[Integer.parseInt(p[0])][Integer.parseInt(p[1])] = val;
        }
    }
}