class Solution {
    static String three = "";
    static int sum = 0, mul = 1;
    public int solution(int n) {
        if(n<3) return n; // 3보다 작으면 바로 리턴
        while(n>=3){
            three += n%3;
            n /= 3;
        }
        three += n;
        for(int i=three.length()-1; i>=0; i--){
            sum += mul*(three.charAt(i)-'0');
            mul *= 3;
        }
        return sum;
    }
}