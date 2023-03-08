class Solution {
    public long[] solution(int x, int n) {
        long[] r = new long[n];
        r[0] = x;
        for(int i=1; i<n; i++){
            r[i] += r[i-1]+r[0];
        }
        return r;
    }
}