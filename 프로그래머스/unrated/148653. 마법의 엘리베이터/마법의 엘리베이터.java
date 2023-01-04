class Solution {
    public int solution(int s) {
        int rock = 0;
        while(s>0){
            int m = s%10;
            int x = check(s/10);
            if(m == 5){
                if(x>=5){
                    rock += 10-m;
                    s/=10;
                    s++;
                }else{
                    rock += m;
                    s/=10;
                }
            }else if(m > 5){
                rock += 10-m;
                s/=10;
                s++;
            }
            else{
                rock += m;
                s/=10;
            }
            
        }
        return rock;
    }
    public int check (int x){
        return x%10;
    }
}