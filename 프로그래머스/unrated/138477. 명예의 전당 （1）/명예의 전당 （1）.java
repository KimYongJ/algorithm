import java.util.*;

class Solution {
    public int[] solution(int k, int[] s) {
        int[] result = new int[s.length];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<s.length; i++){
            pq.add(s[i]);
            if(pq.size()>k)
                pq.poll();
            result[i] = pq.peek();
        }
        
        return result;
    }
}