import java.util.*;

class Solution {
    public int[] solution(int k, int[] s) {
        int[] result = new int[s.length];
        int[] rank = new int[k];
        
        for(int i=0;i< s.length; i++){
            if(i<k)
                rank[0]=s[i];
            else
                if(rank[0]<s[i])
                    rank[0] = s[i];
            Arrays.sort(rank);
            result[i] = i<k ? rank[k-1-i] : rank[0];
        }
        
        
        return result;
    }
}