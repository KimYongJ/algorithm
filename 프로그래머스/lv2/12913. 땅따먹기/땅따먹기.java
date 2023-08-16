class Solution {
    public int solution(int[][] land) {
        int len = land.length;
        
        for(int i=1; i<len; i++){
            land[i][0] += Math.max(Math.max(land[i-1][1],land[i-1][2]),land[i-1][3]);
            land[i][1] += Math.max(Math.max(land[i-1][0],land[i-1][2]),land[i-1][3]);
            land[i][2] += Math.max(Math.max(land[i-1][1],land[i-1][0]),land[i-1][3]);
            land[i][3] += Math.max(Math.max(land[i-1][1],land[i-1][2]),land[i-1][0]);
        }
        len--;
        return Math.max(Math.max(land[len][0],land[len][1]),Math.max(land[len][2],land[len][3]));
    }
}