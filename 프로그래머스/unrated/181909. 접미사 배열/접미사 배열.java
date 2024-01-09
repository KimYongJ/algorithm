import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public ArrayList<String> solution(String my_string) {
        ArrayList<String> list = new ArrayList<>();
        for(int i=0; i<my_string.length(); i++)
            list.add(my_string.substring(i));
        Collections.sort(list);
        return list;
        
    }
}