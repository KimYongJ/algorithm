class Solution {
    public int[] solution(int n, int m) {
        return gcd(n,m,n,m);
    }
    public int[] gcd(int a, int b,int n, int m){
        if(b==0) return new int[]{a,n*m/a};
        return gcd(b,a%b,n,m);
    }
}