class Solution {
    public int solution(int[][] d) {
        int x = -1, idx = 0;
        while(true)
            for(int i=1; i<4; i++)
                if(x<0 && d[idx][0] == d[i][0]){
                    x = Math.abs(d[idx][1]-d[i][1]);
                    idx = i;
                }else if(x>0 && d[idx][1] == d[i][1])
                    return x*Math.abs(d[idx][0]-d[i][0]);
    }
}