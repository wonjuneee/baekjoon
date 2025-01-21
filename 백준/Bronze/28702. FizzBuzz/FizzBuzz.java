import java.util.*;
import java.io.*;

class Solution{
    public int solution(String[] arr){
        int[] result = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            result[i] = compare(arr[i]);
            if(result[i] == 0){
                result[i] = Integer.parseInt(arr[i]);
                return result[i] + (arr.length - i);
            }
        }

        return result[arr.length - 1] + 1;
    }

    public int compare(String str){
        if(str.equals("Fizz"))
            return 3;
        else if(str.equals("Buzz"))
            return 5;
        else if (str.equals("FizzBuzz"))
            return 15;
        else
            return 0;
    }
}

public class Main {
    static String[] arr = new String[3];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 3; i++)
            arr[i] = br.readLine();
        br.close();

        Solution sol = new Solution();
        int result = sol.solution(arr);

        if(result % 15 == 0)
            bw.write("FizzBuzz");
        else if(result % 5 == 0)
            bw.write("Buzz");
        else if(result % 3 == 0)
            bw.write("Fizz");
        else
            bw.write(String.valueOf(result));

        bw.flush();
        bw.close();
    }
}
