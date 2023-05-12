class Solution {
    public int solution(int n) {
        int sum = 0;
        
        for(int i=n%2; i<=n; i+=2)
           sum += n%2==0 ? i*i : i;
        
        return sum;
    }
}