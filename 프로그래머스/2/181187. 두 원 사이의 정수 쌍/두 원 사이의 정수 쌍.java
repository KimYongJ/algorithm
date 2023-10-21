// https://github.com/KimYongJ/algorithm
class Solution {
    public long solution(long r1, long r2) {
        long result = 0;
        for(int x=1; x<=r2; x++){
            int minY = (int)Math.ceil(Math.sqrt((r1*r1-(long)x*x)));
            int maxY = (int)Math.sqrt(r2*r2-(long)x*x);
            result += maxY-minY+1;
        }
        return result*4;
    }
}