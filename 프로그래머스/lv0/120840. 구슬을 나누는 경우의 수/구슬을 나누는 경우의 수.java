class Solution {
    public int solution(int n, int m) {
        return fac(n,m);
    }
    public int fac(int n, int m){
        if(m==0 || n ==m ){
            return 1;
        }else{
            return fac(n-1,m-1)+fac(n-1,m);
        }
    }
}