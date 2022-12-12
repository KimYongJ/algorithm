class Solution {
    public int solution(int n) {
        int r=0;
        for(int i=4;i<=n;i++){
            int cnt =0;
            for(int j=1;j<=i;j++){
                if(i%j==0) cnt++;
                if(cnt==3){
                    r++;
                    break; 
                }
            } 
        }
        return r;
    }
}