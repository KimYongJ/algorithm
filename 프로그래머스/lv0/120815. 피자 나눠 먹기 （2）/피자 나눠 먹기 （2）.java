class Solution {
    public int solution(int n) {
        int x=0;
        for(int i=1;i<=50;i++){
            if((i*6)%n==0){
                x=i;
                break;
            }
        }
        return x;
    }
}