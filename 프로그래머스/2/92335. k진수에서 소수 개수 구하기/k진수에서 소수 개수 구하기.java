class Solution {
    public int solution(int n, int k) {
    
        long convertedNumber = 0, mul = 1;
        while (n != 0) {
            convertedNumber += (n % k) * mul;
            n /= k;
            mul *= 10;
        }
        
        String numberStr = String.valueOf(convertedNumber);
        String[] betweenZeroStr = numberStr.split("0");
        
        int answer = 0;
        for (String str: betweenZeroStr) {

            // 00 이 존재할 경우 빈 문자열이 포함될 수 있으므로 필터링 필요
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
