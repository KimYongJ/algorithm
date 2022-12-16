import java.util.*;
class Solution {
    public String solution(String s) {
        String result ="";
        for(char c : s.toCharArray()){
            if(compare(s,c)){
                result +=c+"";
            }
        }
        char[] r = result.toCharArray();
        Arrays.sort(r);
        return new String(r);
    }
    private static boolean compare(String s, char c){
        if(s.length()-s.replaceAll(c+"","").length()==1){
            return true;
        }else{
            return false;
        }
    }
    
}