import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(String myString) {
        ArrayList<Integer> result = new ArrayList<>();
        String[] token = myString.split("x");
        
        for(String t : token)
            result.add( t.length() );
        
        if(myString.charAt(myString.length()-1) == 'x' )
            result.add(0);
        
        return result;
    }
}