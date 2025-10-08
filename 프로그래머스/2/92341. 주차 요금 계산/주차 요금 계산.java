import java.io.*;
import java.util.*;

class Solution {
    
    final String DEFAULT_OUT_TIME = "23:59";
    
    Map<String, String> inOutRecordMap = new HashMap<>();
    Map<String, Integer> vehicleInTimeMap = new HashMap<>();
    Map<String, Integer> vehicleFeeMap = new HashMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        int baseTime = fees[0], baseFee = fees[1], unitTime = fees[2], unitFee = fees[3];
        
        for (String record: records) {
            String[] rawRecord = record.split(" ");
            String time = rawRecord[0], vehicleNum = rawRecord[1], content = rawRecord[2];
            
            switch (content) {
                case "IN":
                    inOutRecordMap.put(vehicleNum, time);
                    break;
                case "OUT":
                    String inTime = inOutRecordMap.get(vehicleNum);
                    int inTimeInMinute = calculateTimeInMinute(inTime, time);
                    vehicleInTimeMap.put(vehicleNum, vehicleInTimeMap.getOrDefault(vehicleNum, 0) + inTimeInMinute);
                    
                    inOutRecordMap.remove(vehicleNum);
                    break;
                default: break;
            }
        }
        
        for (Map.Entry<String, String> entry : inOutRecordMap.entrySet()) {
            String vehicleNum = entry.getKey(), inTime = entry.getValue();
            
            int inTimeInMinute = calculateTimeInMinute(inTime, DEFAULT_OUT_TIME);
            vehicleInTimeMap.put(vehicleNum, vehicleInTimeMap.getOrDefault(vehicleNum, 0) + inTimeInMinute);
        }
        
        for (Map.Entry<String, Integer> entry : vehicleInTimeMap.entrySet()) {
            String vehicleNum = entry.getKey();
            int time = entry.getValue();
            
            int fee = time <= baseTime ? baseFee : baseFee + (int) Math.ceil((double) (time - baseTime) / unitTime) * unitFee;
            
            vehicleFeeMap.put(vehicleNum, fee);
        }
        
        int[] answer = vehicleFeeMap.keySet().stream().sorted(Comparator.naturalOrder()).map(vehicleFeeMap::get).filter(Objects::nonNull).mapToInt(Integer::valueOf).toArray();
        return answer;
    }
    
    int calculateTimeInMinute(String in, String out) {
        String[] inTime = in.split(":"), outTime = out.split(":");
        int inH = convertStrIntoInt(inTime[0]), inM = convertStrIntoInt(inTime[1]), outH = convertStrIntoInt(outTime[0]), outM = convertStrIntoInt(outTime[1]);
        
        if (outM < inM) {
            outM += 60;
            outH -= 1;
        }
        
        int dHour = outH - inH, dMinute = outM - inM;
        int totalMinute = dMinute + dHour * 60;
        
        return totalMinute;
    }
    
    int convertStrIntoInt(String str) {
        return Integer.parseInt(str);
    }
}