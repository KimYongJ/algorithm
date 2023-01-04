class Solution {
    public int solution(int s) {
        int rock = 0;
        while(s>0){
            int m = s%10;
            int x = s/10%10;
            if(m==5&&x>=5 || m>5){
                rock += 10-m;
                s+=10;
            }else
                rock += m;
            s/=10;
        }
        return rock;
    }
}
