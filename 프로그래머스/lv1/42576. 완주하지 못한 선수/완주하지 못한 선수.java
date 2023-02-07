import java.util.*;
class Solution {
    public String solution(String[] all, String[] str) {
        Arrays.sort(all);
        Arrays.sort(str);
        
        for(int i=0; i<str.length; i++)
            if(!all[i].equals(str[i]))
                return all[i];
        
        return all[all.length-1];
    }
}