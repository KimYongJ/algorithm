class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        long dummy=x;
        for(int i=0; i<n; i++){
            answer[i] = dummy;
            dummy += x;
        }
        return answer;
    }
}