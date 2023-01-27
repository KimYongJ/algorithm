import java.util.*;
class Solution {
    public int[] solution(int[] n) {
        Set<Integer> s = new TreeSet();
        
        for(int i=0; i<n.length-1; i++)
            for(int j=i+1; j<n.length; j++)
                s.add(n[i]+n[j]);
        
        int[] arr = new int[s.size()];
        int idx = 0;
        Iterator i = s.iterator();
        while(i.hasNext()){
            arr[idx++] = (int)i.next();
        }
        return arr;
    }
}