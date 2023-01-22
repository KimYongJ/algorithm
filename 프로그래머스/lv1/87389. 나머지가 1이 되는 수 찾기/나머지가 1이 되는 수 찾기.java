class Solution {
    public int solution(int n) {
        n = n-1;
        for(int i=2; i*i<=n; i++){
            if(n%i==0)
                return i;
        }
        return n;
    }
}