import java.util.*;
import java.io.*;

public class Main {

    public static String solution(char[] func, String[] arr){
        Stack<Character> stack = new Stack<>();
        int countDeletion = 0;
        for(char str: func){
            if(str == 'R') {
                if (stack.isEmpty() || stack.peek() != str)
                    stack.push(str);
                else
                    stack.pop();
            }
            else if(str == 'D'){
                if(arr.length < ++countDeletion)
                    return "error";
                stack.push(str);
            }
        }
        if(countDeletion == arr.length)
            return "[]";

        Deque<Integer> array = new ArrayDeque<>();
        for(String a: arr)
            array.addLast(Integer.parseInt(a));

        boolean isReversed = false;
        for(char str: stack) {
            switch (str) {
                case 'R':
                    isReversed = !isReversed;
                    break;
                case 'D':
                    if (isReversed)
                        array.removeLast();
                    else
                        array.removeFirst();
                    break;
                default:
                    break;
            }
        }
        if(isReversed) {
            ArrayList<String> result = new ArrayList<>() {
            };
            for (Iterator<Integer> iter = array.descendingIterator(); iter.hasNext(); ) {
                int ele = iter.next();
                result.add(String.valueOf(ele));
            }
            return result.toString().replaceAll(" ", "");
        }
        return array.toString().replaceAll(" ", "");
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++){
            char[] function = br.readLine().toCharArray();
            int n = Integer.parseInt(br.readLine());
            String rawArr = br.readLine();
            String[] arr = rawArr.substring(1, rawArr.length() - 1).split(",");
            if (rawArr.equals("[]"))
                arr = new String[]{};
            String result = solution(function, arr);
            bw.write(result);
            bw.newLine();
        }
        br.close();

        bw.flush();
        bw.close();
    }
}
