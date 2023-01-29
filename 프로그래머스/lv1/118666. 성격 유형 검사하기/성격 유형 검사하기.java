import java.util.*;
class Solution {
    static HashMap<Character,Integer> m;
    public String solution(String[] s, int[] c) {
        m = new HashMap<>();
        char[][] ch = {{'R','T'},{'C','F'},{'J','M'},{'A','N'}};
        int[] num = {0,3,2,1,0,1,2,3};
        for(char[] j : ch){
            m.put(j[0],0);
            m.put(j[1],0);
        }
        
        for(int i=0; i<s.length; i++){
            if(c[i]<=3)
                m.put(s[i].charAt(0),m.get(s[i].charAt(0))+num[c[i]]);
            else
                m.put(s[i].charAt(1),m.get(s[i].charAt(1))+num[c[i]]);
        }
            
        String result = "";
        
        for(int i=0; i<4; i++)
            result += m.get(ch[i][0]) >= m.get(ch[i][1]) ? ch[i][0]  : ch[i][1];
        
        return result;        
    }
}