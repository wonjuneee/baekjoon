import java.io.*;
import java.util.*;

public class Main {
    public static int n;

    public static final Stack<Long> numberStack = new Stack<>();
    public static final List<String> operationList = new ArrayList<>();
    public static final List<Long> inputList = new ArrayList<>();
    public static final int MAX_NUMBER = 1000000000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            try {
                while (operationList.isEmpty() || !operationList.get(operationList.size() - 1).equals("END")) {
                    String operation = br.readLine();
                    if (operation.equals("QUIT")) {
                        throw new Exception();
                    } else if (operation.isEmpty()) {
                        continue;
                    }
                    operationList.add(operation);
                }

                n = Integer.parseInt(br.readLine());
                for (int i = 0; i < n; i++) {
                    inputList.add(Long.parseLong(br.readLine()));
                }

                for (Long number : inputList) {
                    try {
                        numberStack.push(number);
                        long result = solution();
                        bw.write(result == Integer.MIN_VALUE ? "ERROR" : String.valueOf(result));
                        bw.newLine();
                    } catch (EmptyStackException e) {
                        bw.write("ERROR");
                        bw.newLine();
                    }
                    numberStack.clear();
                }
                operationList.clear();
                inputList.clear();
                bw.newLine();
            } catch (Exception e) {
                break;
            }
        }
        bw.flush();
        bw.close();
    }

    private static long solution() {
        for (String operation : operationList) {
            long num1, num2;
            switch (operation) {
                case "END":
                    break;
                case "POP":
                    numberStack.pop();
                    break;
                case "INV":
                    numberStack.push(-numberStack.pop());
                    break;
                case "DUP":
                    numberStack.push(numberStack.peek());
                    break;
                case "SWP":
                    num1 = numberStack.pop();
                    num2 = numberStack.pop();
                    numberStack.push(num1);
                    numberStack.push(num2);
                    break;
                case "ADD":
                    numberStack.push(numberStack.pop() + numberStack.pop());
                    if (isOverRange(numberStack.peek())) {
                        return Integer.MIN_VALUE;
                    }
                    break;
                case "SUB":
                    numberStack.push(-numberStack.pop() +  numberStack.pop());
                    if (isOverRange(numberStack.peek())) {
                        return Integer.MIN_VALUE;
                    }
                    break;
                case "MUL":
                    numberStack.push(numberStack.pop() * numberStack.pop());
                    if (isOverRange(numberStack.peek())) {
                        return Integer.MIN_VALUE;
                    }
                    break;
                case "DIV":
                    num1 = numberStack.pop();
                    num2 = numberStack.pop();
                    if (num1 == 0L) {
                        return Integer.MIN_VALUE;
                    }
                    boolean isNegForDiv = (num1 < 0 && num2 > 0) || (num1 > 0 && num2 < 0);
                    long div = Math.abs(num2) / Math.abs(num1);
                    numberStack.push(isNegForDiv ? -div : div);
                    break;
                case "MOD":
                    num1 = numberStack.pop();
                    num2 = numberStack.pop();
                    if (num1 == 0L) {
                        return Integer.MIN_VALUE;
                    }
                    boolean isNegForMod = num2 < 0;
                    long mod = Math.abs(num2) % Math.abs(num1);
                    numberStack.push(isNegForMod ? -mod : mod);
                    break;
                default: // "NUM X"
                    long number = Long.parseLong(operation.split(" ")[1]);
                    numberStack.push(number);
            }
        }

        if (numberStack.size() == 1) {
            return numberStack.pop();
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private static boolean isOverRange (long number) {
        return number > MAX_NUMBER || number < -MAX_NUMBER;
    }
}