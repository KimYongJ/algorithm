import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String my_string) {
        ArrayList<String> list = new ArrayList<>();
        for(String s : my_string.split(" "))
            if(s.length()>0)
                list.add(s);
        return list;
    }
}