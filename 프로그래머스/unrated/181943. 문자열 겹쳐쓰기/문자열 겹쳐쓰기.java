class Solution {
    public String solution(String str, String ostr, int s) {
        char[] c = str.toCharArray();
        for(int i=0; i<ostr.length(); i++)
            c[s+i] = ostr.charAt(i);
        
        return String.valueOf(c);
    }
}