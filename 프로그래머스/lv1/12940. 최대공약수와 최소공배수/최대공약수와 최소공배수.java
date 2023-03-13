class Solution {
    public int[] solution(int n, int m) {
        int gcd = gcd(n,m);
        return new int[]{gcd,n*m/gcd};
    }
    public int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}