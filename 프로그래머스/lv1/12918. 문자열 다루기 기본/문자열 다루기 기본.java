// https://github.com/KimYongJ
class Solution {
    public boolean solution(String s) {
        if(s.length()!=4&&s.length()!=6)
            return false;
        for(char c : s.toCharArray()){
            if(c<'0' || '9'<c)
                return false;
        }
        return true;
    }
}