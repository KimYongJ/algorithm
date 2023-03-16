class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean check = true;
        for(char c : s.toCharArray()){
            if(c==' '){
                check = true;
                sb.append(" ");
                continue;
            }
            sb.append(check ? Character.toUpperCase(c) : Character.toLowerCase(c));
            check = !check;
        }

        
        return sb.toString();
    }
}