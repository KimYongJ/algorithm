class Solution {
    static int cnt,num;
    public int solution(int n) {
        for(int x=1; x<=n; x++){
            num = 0;
            for(int i=x; i<=n; i++){
                num += i;
                if(n==num){
                    cnt++;
                    break;
                }else if(num>n) break;
            }
        }
        return cnt;
    }
}