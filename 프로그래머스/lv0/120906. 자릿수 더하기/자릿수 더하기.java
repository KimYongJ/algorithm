class Solution {
    public int solution(int n) {
        int result=0;
        for(int i=0;i<6;i++){
            result+= n%10;
            n/=10;
        }
        return result;     
    }
}