import java.io.*;
class Solution {
    public String solution(String my_string, String letter) {
    	 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
         String result="";
         char c = letter.charAt(0);
         for(int i=0;i<my_string.length();i++){
             char data = my_string.charAt(i);
             if(data==c)continue;
             result += String.valueOf(data);
         }
        return result;
    }
}