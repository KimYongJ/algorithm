import java.util.*;
class Solution {
    public String solution(String letter) {
        String[] morse = {
    ".-","-...","-.-.","-..",".","..-.",
    "--.","....","..",".---","-.-",".-..",
    "--","-.","---",".--.","--.-",".-.",
    "...","-","..-","...-",".--","-..-",
    "-.--","--.."
        };
        String[] data = letter.split(" ");
        String result ="";
        for(int i=0;i<data.length;i++)
            for(int j=0;j<morse.length;j++)
                if(data[i].equals(morse[j]))
                    result+=(char)(j+97);

            
        return result;
    }
}