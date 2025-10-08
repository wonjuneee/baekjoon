class Solution {


    
    public int solution(int n, int k) {
    
        long convertedNumber = 0, mul = 1;
        while (n != 0) {
            convertedNumber += (n % k) * mul;
            n /= k;
            mul *= 10;
        }
        
        String numberStr = String.valueOf(convertedNumber);
        // System.out.println("numberStr: " + numberStr);
        String[] primesStr = numberStr.split("0");
        
        // System.out.println("소수후보 수: ");
        int answer = 0;
        for (String str: primesStr) {
            // System.out.println('-' + str);
            if (str.equals("")) {
                continue;
            }
            long longNum = Long.parseLong(str);
            if (isPrime(longNum)) {
                answer++;
            }
        }
        
        
        
        return answer;
    }
    
    boolean isPrime(long number) {
        if (number == 1) return false;
        
        long rootNumber = (long) Math.sqrt(number);
        
        for (int i = 2; i <= rootNumber; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}