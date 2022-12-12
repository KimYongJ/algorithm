import java.util.*;
class Solution {
    public int solution(String before, String after) {
        char[] a = before.toCharArray();
        char[] b = after.toCharArray();
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        String a1=new String(a);
        String b1=new String(b);
        
        return a1.equals(b1) ? 1:0;

    }
}