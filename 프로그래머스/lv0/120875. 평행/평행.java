class Solution {
    public int solution(int[][] d) {
        float[] p = new float[6];
        int idx = 0;
        for(int i=0; i<3; i++)
            for(int j=i+1; j<4; j++)
                p[idx++] = (float)(d[i][1]-d[j][1])
                    /(float)(d[i][0]-d[j][0]);
        
        for(int i=0; i<5; i++)
            for(int j=i+1; j<6; j++)
                if(p[i] == p[j])
                    return 1;   
        return 0;
    }
}