class Solution {
    public int solution(int[][] size) {
        int w=0, h=0;
        for(int[] s : size){
            w = Math.max(w,Math.max(s[0],s[1]));
            h = Math.max(h,Math.min(s[0],s[1]));
        }
        return w*h;
    }
}