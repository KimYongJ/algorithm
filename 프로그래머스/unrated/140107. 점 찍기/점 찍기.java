// https://github.com/KimYongJ
class Solution {
    public long solution(int k, int d) {
        long r = 0;
        for(long x=0; x<=d; x+=k){
            long y = (long)Math.sqrt(Math.pow((long)d,2)-Math.pow((long)x,2));
            r += y/k+1;
        }
        
        
        return r;
    }
}