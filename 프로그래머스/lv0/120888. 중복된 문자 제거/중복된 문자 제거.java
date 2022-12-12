import java.util.*;
class Solution {
    public String solution(String my_string) {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        
        String r ="";
        for(char c : my_string.toCharArray()){
            set.add(c+"");
        }
        Iterator i = set.iterator();
        while(i.hasNext()){
            r+=i.next();
        }
        return r;
    }
}