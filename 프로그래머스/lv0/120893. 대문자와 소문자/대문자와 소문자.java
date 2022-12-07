class Solution {
    public String solution(String my_string) {
        String result="";
        for(char c : my_string.toCharArray()){
            if(Character.isLowerCase(c)){
                result+=Character.toUpperCase(c)+"";
            }else{
                result+=Character.toLowerCase(c)+"";
            }            
        }
        return result;
    }
}