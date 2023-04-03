import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public String[] solution(String[] slist, int n) {
        ArrayList<String> list = new ArrayList<>();
        
        for(String s : slist)
            list.add(s.charAt(n) + s);
        
        Collections.sort(list);
        
        for(int i=0; i<slist.length; i++)
            slist[i] = list.get(i).substring(1);
        
        return slist;
    }
}