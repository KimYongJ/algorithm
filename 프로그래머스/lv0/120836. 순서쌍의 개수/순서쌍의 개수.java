class Solution {
    public int solution(int n) {
        int result = 1;
        for(int i=1;i<n/2+1;i++){
            if(n%i==0) ++result;
        }
        return result;
    }
}