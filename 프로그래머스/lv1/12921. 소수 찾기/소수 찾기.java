// https://github.com/KimYongJ
class Solution {
    public int solution(int n) {
        int result =0;
        
        boolean[] prime = new boolean[n+1];
        
        int root = (int)Math.sqrt(n);
        
        for(int i=2; i<=root; i++){
            if(!prime[i]){
                for(int j=i; i*j<=n; j++)
                    prime[i*j] = true;
            }
        }
        
        for(int i=2; i<=n; i++){
            if(!prime[i])
                result++;
        }
        
        return result;
    }
}