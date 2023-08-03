class Solution {
    public int solution(String s, String p) {
        if(s.length()<p.length()) 
            return 0;
        s = s.substring(0,p.length());
        return s.equals(p) ? 1 : 0;
    }
}