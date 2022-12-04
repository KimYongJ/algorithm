class Solution {
    public int solution(int hp) {
        int cnt=0;
        for(int i=5;i>0;i-=2){
            cnt+=hp/i;
            hp%=i;
        }
        return cnt;
    }
}