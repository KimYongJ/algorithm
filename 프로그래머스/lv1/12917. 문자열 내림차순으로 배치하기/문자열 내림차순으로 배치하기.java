import java.util.Arrays;
class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        char[] list = s.toCharArray();
        
        Arrays.sort(list);
        
        for(char c : list)
            sb.append(c);
        
        return sb.reverse().toString();
    }
}