class Solution {
    static String three = "";
    public int solution(int n) {
        if(n<3) return n; // 3보다 작으면 바로 리턴
        while(n>=3){
            three += n%3;
            n /= 3;
        }
        three += n;

        return Integer.parseInt(three,3);
    }
}