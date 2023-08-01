// https://github.com/KimYongJ
class Solution {
    static private int result;
    public int solution(int n, int k) {
        for(String s : Integer.toString(n,k).split("0"))
            if(s.length()>0 && prime(Long.parseLong(s)))
                result++;
        
        return result;
    }
    public boolean prime(long num){
        if(num<2) return false;
        for(long i=2; i*i<=num; i++)
            if(num%i==0) return false;
        return true;
    }
}