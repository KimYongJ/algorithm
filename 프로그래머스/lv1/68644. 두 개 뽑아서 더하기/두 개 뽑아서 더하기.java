import java.util.*;
class Solution {
    public Integer[] solution(int[] n) {
        Set<Integer> s = new HashSet<>();
        
        for(int i=0; i<n.length-1; i++)
            for(int j=i+1; j<n.length; j++)
                s.add(n[i]+n[j]);

        Integer[] arr = s.stream().toArray(Integer[]::new);
        Arrays.sort(arr);
        return arr;
    }
}