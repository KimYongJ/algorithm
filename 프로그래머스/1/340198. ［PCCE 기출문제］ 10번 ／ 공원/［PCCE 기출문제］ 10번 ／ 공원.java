//[PCCE 기출문제] 10번 / 공원
import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        
        Arrays.sort(mats);

        int ymax = park.length;
        int xmax = park[0].length;
        
        for(int i=mats.length - 1; i>=0; i--)
        {
            int len = mats[i];

            for(int x=0; x <= xmax - len; x++)
                for(int y=0; y <= ymax - len; y++)
                    if(isPos(x, y, len, park))
                        return len;
        }
        return -1;
    }
    public boolean isPos(int x, int y, int len, String[][] park){
        for(int x1 = x; x1 < x+len; x1++)
            for(int y1 = y; y1 < y+len; y1++)
                if(!"-1".equals(park[y1][x1]))
                    return false;
        
        return true;
    }
}