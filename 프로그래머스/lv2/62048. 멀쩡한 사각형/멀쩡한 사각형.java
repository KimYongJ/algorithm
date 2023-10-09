// https://github.com/KimYongJ/algorithm
class Solution {
    public long solution(long w, long h) {
        long div = gcd(w,h); // 최대 공약수 
        
        return w*h - ( (w/div + h/div - 1) * div );
    }
    public long gcd(long a, long b ){
        return b==0 ? a : gcd(b,a%b);
    }
}