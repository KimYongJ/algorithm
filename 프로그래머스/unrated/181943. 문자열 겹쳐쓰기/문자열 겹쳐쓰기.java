class Solution {
    public String solution(String str, String ostr, int s) {
        return str.substring(0,s)
                +ostr
                +str.substring(s+ostr.length(),str.length());
    }
}