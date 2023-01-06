class Solution {
    public int solution(int n) {
        int cnt = 0;
        for(int i=1; i<=n; i++)
            if(ThreeCheck(i)) cnt++;
        while(cnt>0)
            if(!ThreeCheck(++n)) cnt--;
        return n;
    }
    public boolean ThreeCheck(int n){
        if(n%3==0) return true;
        for(char c : String.valueOf(n).toCharArray())
            if(3==c-'0') return true;
        return false;
    }
}