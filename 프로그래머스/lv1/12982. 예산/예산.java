import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int i = 0;
        Arrays.sort(d);
        for(; i<d.length; i++){
            budget -= d[i];
            
            if(budget<0) 
                break;

        }
        return i++;
    }
}