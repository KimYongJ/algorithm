class Solution {
    public int solution(int[] a, boolean[] s) {
        int r = 0;
        for(int i=0; i<a.length; i++)
            r += s[i] ? a[i] : a[i]*-1;        
        return r;
    }
}