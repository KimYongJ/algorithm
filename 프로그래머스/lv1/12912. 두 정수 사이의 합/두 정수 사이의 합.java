class Solution {
    public long solution(int a, int b) {
        long answer = 0;
        if(a>b){
            int dummy = a;
            a = b;
            b = dummy;
        }
        for(int i=a; i<=b; i++)
            answer += i;
        
        return answer;
    }
}