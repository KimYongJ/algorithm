class Solution {
    public int solution(String my_string) {
        int result = 0;
        for(char c : my_string.toCharArray()){
            if(Character.isDigit(c)){
                result += c-'0';
            }
        }
        return result;
    }
}