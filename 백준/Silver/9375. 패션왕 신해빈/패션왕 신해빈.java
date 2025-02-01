import java.util.*;
import java.io.*;

public class Main {

    public static int solution(int numberOfWearable, String[] wearable){
        HashMap<String, Integer> distinct = new HashMap<>();
        for(int i = 0; i < numberOfWearable; i++){
            String type = wearable[i].split(" ")[1];
            if(!distinct.containsKey(type)){
                distinct.put(type, 2);
            }
            else{
                distinct.put(type, distinct.get(type) + 1);
            }
        }
        int result = 1;
        for(int count: distinct.values()){
            result *= count;
        }

        return result - 1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            String[] wearable = new String[30];
            int numberOfWearable = Integer.parseInt(br.readLine());
            for(int j = 0; j < numberOfWearable; j++){
                wearable[j] = br.readLine();
            }
            int result = solution(numberOfWearable, wearable);
            bw.write(String.valueOf(result));
            bw.newLine();
        }
        br.close();

        bw.flush();
        bw.close();
    }
}
