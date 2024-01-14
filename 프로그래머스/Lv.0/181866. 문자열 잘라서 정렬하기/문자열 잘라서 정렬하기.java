import java.util.Collections;
import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String myString) {
        ArrayList<String> list = new ArrayList<>();
        for(String s : myString.split("x"))
            if(s.length()>0)
                list.add(s);
        Collections.sort(list);
        return list;
    }
}