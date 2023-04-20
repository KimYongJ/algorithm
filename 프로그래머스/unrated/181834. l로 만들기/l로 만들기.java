class Solution {
    public String solution(String str) {
        StringBuilder sb = new StringBuilder();
        
        for(char c : str.toCharArray())
            sb = c<'l' ? sb.append('l') : sb.append(c);
        
        return sb.toString();
    }
}