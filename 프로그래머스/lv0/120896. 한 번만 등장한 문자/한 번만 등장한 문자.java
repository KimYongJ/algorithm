import java.util.*;
class Solution {
    public String solution(String s) {
        String x = "";
        char[] cl = s.toCharArray();
        for(int i=0;i<cl.length;i++){
            int cnt=0;
            for(int j=0;j<cl.length;j++){
                if(cnt>1) break;
                if(cl[i]==cl[j]){
                    cnt++;
                }
            }
            if(cnt==1) x+= cl[i]+"";
        }
        char[] result = x.toCharArray();
        Arrays.sort(result);
        return new String(result);
    }
}