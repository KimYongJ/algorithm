class Solution {
    public int solution(int[] s) {
        // 가장긴변이 배열중 가장큰것일 경우
        // 범위 = 배열중 가장큰것 >= X > 배열중가장큰수-작은수
        // 가장긴게 지정되지 않을 경우 
        // 범위 : 작은숫자 두개의합 > X > 배열중 가장큰수
        int n=0,m=0;
        if(s[0]>s[1]){
            m = s[0];
            n = s[1];
        }else{
            m = s[1];
            n = s[0];
        }
        return 2*n-1;
    }
}