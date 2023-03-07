import java.util.*;
class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] result = new int[targets.length];
        int[] count = new int[26];
        Arrays.fill(count,101);
        for(String s : keymap)
            for(int i=0;i<s.length(); i++)
                if(count[s.charAt(i)-'A']>i+1)
                    count[s.charAt(i)-'A']=i+1;

        
        for(int i=0; i<targets.length; i++){
            for(char c : targets[i].toCharArray()){
                if(count[c-'A']==101){
                    result[i] = -1;
                    break;
                }else
                    result[i] += count[c-'A'];
            }
        }
        
        
        
        return result;
    }
}