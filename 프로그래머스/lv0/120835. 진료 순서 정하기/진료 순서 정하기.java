import java.util.*;
class Solution {
    public int[] solution(int[] emergency) {
        int len = emergency.length;
        int[] result = new int[len];
        System.arraycopy(emergency,0,result,0,len);
        int [] r = new int[len];
        Arrays.sort(emergency);
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(emergency[i]==result[j]){
                    r[j]=len-i;
                    break;
                }
            }
        }
        return r;
        
    }
}