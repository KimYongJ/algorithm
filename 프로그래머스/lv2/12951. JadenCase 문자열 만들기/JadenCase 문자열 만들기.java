class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean check = true;
        for(char c : s.toLowerCase().toCharArray()){
            if(c == ' '){
                check = true;
                sb.append(" ");
                continue;
            }
            if(check){
                c = Character.toUpperCase(c);
                check = false;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}