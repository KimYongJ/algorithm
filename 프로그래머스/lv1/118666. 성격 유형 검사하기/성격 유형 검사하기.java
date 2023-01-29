import java.util.*;
class Solution {
    static HashMap<Character,Integer> m;
    public String solution(String[] s, int[] c) {
        m = new HashMap<>();
        m.put('R',0);m.put('T',0);m.put('C',0);m.put('F',0);m.put('J',0);
        m.put('M',0);m.put('A',0);m.put('N',0);
        
        for(int i=0; i<s.length; i++)
            check(s[i],c[i]);
        
        String result = "";
        
        result += m.get('R') >= m.get('T') ? 'R'  : 'T';
        result += m.get('C') >= m.get('F') ? 'C'  : 'F';
        result += m.get('J') >= m.get('M') ? 'J'  : 'M';
        result += m.get('A') >= m.get('N') ? 'A'  : 'N';
        
        return result;        
    }
    
    
    public void check(String s, int c){
        if(c<=3)
            m.put(s.charAt(0),m.get(s.charAt(0))+choiceNum(c));
        else
            m.put(s.charAt(1),m.get(s.charAt(1))+choiceNum(c));
    }
    
    public int choiceNum(int c){
        if(c==1 || c== 7) return 3;
        else if(c==2 || c==6) return 2;
        else if(c==3 || c==5) return 1;
        else return 0;
    }
}