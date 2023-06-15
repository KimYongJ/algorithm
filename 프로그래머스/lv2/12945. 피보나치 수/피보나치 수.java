class Solution {
    public int solution(int n) {
        return fibo(n);
    }
    public int fibo(int n){
        int a = 0,tmp = 0, b =1;
        for(int i=1; i<n; i++){
            tmp = (a+b)%1234567;
            a=b;
            b=tmp;
        }
        return tmp;
    }
}