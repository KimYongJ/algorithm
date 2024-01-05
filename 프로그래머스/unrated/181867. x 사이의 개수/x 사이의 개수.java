import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(String myString) {
        ArrayList<Integer> result = new ArrayList<>();

        for(String t : myString.split("x"))
            result.add( t.length() );
        
        if(myString.endsWith("x"))
            result.add(0);
        
        return result;
    }
}