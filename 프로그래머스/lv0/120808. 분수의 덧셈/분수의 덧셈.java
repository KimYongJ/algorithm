class Solution {
    public int[] solution(int dn1, int n1, int dn2, int n2) {
        int gcd = gcd(Math.max(n1,n2),Math.min(n1,n2));
        int n = n1*n2/gcd;
        int denum = (dn1*n/n1 + dn2*n/n2);
        
        gcd = gcd(Math.max(n,denum),Math.min(n,denum));
        n/=gcd;
        denum/=gcd;
        
        return new int[] {denum, n};
    }
    int gcd(int a, int b){
        if(b==0) return a;
        return gcd(b,a%b);
    }
}
// 유클리드 호제법 최대 공약수 구한다
// 최소공배수는 두수의 곲 나누기 최대 공약수이다.
// 기약분수로 나타내는 방법은 분모와 분자에 각각 최대 공약수를 나누면 된다.