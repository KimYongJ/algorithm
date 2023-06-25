// https://github.com/KimYongJ
class Solution {
    public long solution(int n) {
        long[] result = new long[2000+1];
        result[1] = 1;
        result[2] = 2;
        for(int i=3; i<n+1; i++)
            result[i] = (result[i-1]+result[i-2])%1234567;
        return result[n];
    }
}