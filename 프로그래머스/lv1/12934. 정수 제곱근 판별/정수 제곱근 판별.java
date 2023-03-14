class Solution {
    public long solution(long n) {
        if(n==1) return 4;
        for(long i=2; i*i<=n; i++)
            if(i*i==n)
                return (i+1)*(i+1);
        return -1;
    }
}