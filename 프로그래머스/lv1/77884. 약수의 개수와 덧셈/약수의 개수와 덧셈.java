class Solution {
    public int solution(int l, int r) {
        int a = 0;
        for(int i=l; i<=r; i++)
            a += check(i)%2==0 ? i : i*-1;
        return a;
    }
    
    private int check(int x){
        if(x<1) return 0;
        else if(x==1) return 1;
        else{
            int cnt = 0;
            for(int i=1; i<=x/2; i++)
                    if(x%i==0) cnt++;
            return ++cnt;
        }
    }
}