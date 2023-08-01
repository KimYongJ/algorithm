// https://github.com/KimYongJ
class Solution {
    static private int result;
    public int solution(int n, int k) {
        Loop : for(String s : Integer.toString(n,k).split("0"))
            if(s.length()>0){
                long num = Long.parseLong(s);
                if(num<2) continue;
                for(long i=2; i*i<=num; i++){
                    if(num%i==0) continue Loop;
                }
                result++;
            }
        return result;
    }
}