import java.util.*;
class Solution {
    public boolean solution(String[] p) {
        Arrays.sort(p);
        for(int x=0; x<p.length-1; x++){
          //  for(int i=x+1; i<p.length; i++){
                if(p[x+1].startsWith(p[x]))
                    return false;
          //  }
        }
        
        
        return true;
    }
}