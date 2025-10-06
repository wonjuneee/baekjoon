import java.io.*;
import java.util.*;

class Solution {
    
    static int k;
    
    static final Map<String, Set<String>> reportedMap = new LinkedHashMap<>();
    static final Map<String, Integer> receivedMailMap = new LinkedHashMap<>();
    
    public int[] solution(String[] id_list, String[] report, int k) {
        
        for (String id: id_list) {
            reportedMap.put(id, new HashSet<>());
            receivedMailMap.put(id, 0);
        }
        
        for (String rep: report) {
            String[] reportContext = rep.split(" ");
            String reporter = reportContext[0]; // 신고한 유저
            String reported = reportContext[1]; // 신고된 유저
            
            reportedMap.get(reported).add(reporter);
        }
        
        for (String reported : id_list) {
            if (reportedMap.get(reported).size() >= k) {
                Set<String> reportedSet = reportedMap.get(reported);
            
                for (String reporter : reportedSet) {
                    receivedMailMap.put(reporter, receivedMailMap.get(reporter) + 1);
                }
            }
        }
        
        // LinkedHashMap으로 순서가 보장되어 있으므로, 해당 values에 대해 mapToInt 후 toArray로 정답 형식 맞추기
        int[] answer = receivedMailMap.values().stream().mapToInt(value -> value).toArray();
        return answer;
    }
}