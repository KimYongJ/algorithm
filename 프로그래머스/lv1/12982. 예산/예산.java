import java.util.*;
class Solution {
    public int solution(int[] d, int budget) {
        int i = 0;
        Arrays.sort(d);
        for(; i<d.length; i++){
            int num = budget-d[i];
            if(num<0) break;
            budget = num;
        }
        return i++;
    }
}