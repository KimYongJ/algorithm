import java.util.PriorityQueue;
import java.util.Collections;
class Solution {
    static int max,idx,result,len;
    public int solution(int[] list, int location) {
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int x : list) q.add(x);
        
        max = q.poll();
        len = list.length;
        while(true){
            if(idx==location && max == list[idx])
                break;
            else if(max==list[idx]){
               // list[idx] = -1;
                max = q.poll();
                result++;
            }
            idx = idx+1==len ? 0 : idx+1;
        }
        return result+1;   
    }
}