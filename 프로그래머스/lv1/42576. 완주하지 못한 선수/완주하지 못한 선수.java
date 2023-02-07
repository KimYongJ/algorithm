import java.util.*;
class Solution {
    public String solution(String[] all, String[] str) {
        String result = "";
        HashMap<String, Integer> m = new HashMap<>();
        
        for(String s : all) m.put(s,m.getOrDefault(s,0)+1);
        for(String s : str) m.put(s,m.get(s)-1);
        
        
        for(String s : all)
            if(0!=m.get(s)){
                result = s;
                break;
        }
        return result;
    }
}