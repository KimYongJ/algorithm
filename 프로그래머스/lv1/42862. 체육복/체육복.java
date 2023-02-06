import java.util.*;
class Solution {
    public int solution(int n, int[] lost, int[] r) {
        Arrays.sort(lost);

        boolean[] tf = new boolean[n+2];
        for(int x : r)
            tf[x] = true;

        List<Integer> lost_list = new ArrayList<>();
        for(int i=0; i<lost.length; i++){
           if(tf[lost[i]]){
               tf[lost[i]] = false;
               continue;
           }
            lost_list.add(lost[i]);
        }

        int len = lost_list.size();
        for(int i=0; i<lost_list.size(); i++){
            int x = lost_list.get(i);
            if(tf[x-1]){
                tf[x-1]=false;
                len--;
            }else if(tf[x+1]){
                tf[x+1]=false;
                len--;
            }
        }
        return n-len;
        
    }
}