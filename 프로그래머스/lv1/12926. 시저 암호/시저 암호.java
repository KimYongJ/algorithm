class Solution {
    public String solution(String s, int n) {
        StringBuilder sb = new StringBuilder();
        
        for(char c : s.toCharArray()){
            if('a'<=c && c<='z'){
                c+=n;
                if('z'<c) c-=26;
            }else if('A'<=c && c<='Z'){
                c+=n;
                if('Z'<c) c-=26;
            }
            sb.append(c);            
        }
        
        return sb.toString();
        
    }
}