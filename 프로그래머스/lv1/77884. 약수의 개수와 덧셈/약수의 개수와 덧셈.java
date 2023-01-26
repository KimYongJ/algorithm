class Solution {
    public int solution(int l, int r) {
        int a =0;
        for(int i=l; i<=r; i++)
            a += i%Math.sqrt(i) == 0 ? i*-1 : i;        
        return a;
    }
}