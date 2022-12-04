class Solution {
    public int solution(int n) {
        int result = 0;
        for(int i=1;i<n+1;i++){
            if(n%i==0) result+=1;
        }
        return result;
    }
}