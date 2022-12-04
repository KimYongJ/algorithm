class Solution {
    public String solution(String my_string, int n) {
        StringBuilder sb = new StringBuilder();
        for(char x : my_string.toCharArray()){
            sb.append((x+"").repeat(n));
        }
        return sb.toString();
    }
}