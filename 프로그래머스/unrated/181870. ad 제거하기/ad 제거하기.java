import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String[] strArr) {
        ArrayList<String> result = new ArrayList<>();
        for(String s : strArr)
            if( !s.contains("ad") )
                result.add(s);
        return result;
    }
}