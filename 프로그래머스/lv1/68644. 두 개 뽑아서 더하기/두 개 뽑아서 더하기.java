import java.util.*;
class Solution {
    public int[] solution(int[] n) {
        Set<Integer> s = new TreeSet();
        
        for(int i=0; i<n.length-1; i++)
            for(int j=i+1; j<n.length; j++)
                s.add(n[i]+n[j]);

        return s.stream().mapToInt(Integer::intValue).toArray();
    }
}