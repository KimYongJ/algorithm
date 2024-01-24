import java.util.ArrayList;
class Solution {
    public ArrayList<Long> solution(String[] intStrs, int k, int s, int l) {
        ArrayList<Long> list = new ArrayList<>();
        
        for(String str : intStrs)
        {
            long num = Long.parseLong( str.substring(s,s+l) );
            if( k < num)
            {
                list.add(num);
            }
            
        }
        
        return list;
    }
}