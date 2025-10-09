import java.io.*;
import java.util.*;

class Solution {
    static final Map<String, Integer> typeValidDurationMap = new HashMap<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new LinkedList<>();
        
        String[] partialToday = today.split("\\.");
        int year = convertStrIntoInt(partialToday[0]), month = convertStrIntoInt(partialToday[1]), day = convertStrIntoInt(partialToday[2]);
        
        for (String term: terms) {
            String[] content = term.split(" ");
            int validDuration = Integer.parseInt(content[1]);
            
            typeValidDurationMap.put(content[0], validDuration);
        }
        
        for (int i = 0; i < privacies.length; i++) {
            String privacy = privacies[i];
            String[] content = privacy.split(" ");
            String date = content[0], type = content[1];
            String[] partialDate = date.split("\\.");
            int pYear = convertStrIntoInt(partialDate[0]), pMonth = convertStrIntoInt(partialDate[1]), pDay = convertStrIntoInt(partialDate[2]);
            
            int duration = typeValidDurationMap.get(type);
            int dYear = duration / 12, dMonth = duration % 12;
            
            // dueDay = pDay 일 때, pDay == 1 인 케이스를 별도로 처리하지 않아도 무방하다.
            // - today의 연월이 같고 day == pDay 인 경우는 유효기간이 지난 케이스이므로 파기 케이스에 포함시키면 된다.
            int dueYear = pYear + dYear, dueMonth = pMonth + dMonth, dueDay = pDay;
            if (pMonth + dMonth > 12) {
                dueYear++;
                dueMonth -= 12;
            }
            
            // '수집일 + 유효기간 < 오늘날짜' 인 케이스
            // dueDate - 1일 까지 유효하므로, dueDate == today 인 경우도 파기 케이스에 포함된다.
            if (dueYear < year || (dueYear == year && dueMonth < month) || (dueYear == year && dueMonth == month && dueDay <= day)) {
                answer.add(i + 1);
            }    
        }
        
        return answer.stream().mapToInt(x->x).toArray();
    }
    
    int convertStrIntoInt(String str) {
        return Integer.parseInt(str);
    }
}