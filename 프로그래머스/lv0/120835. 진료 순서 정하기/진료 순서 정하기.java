import java.util.*;
class Solution {
    public int[] solution(int[] emergency) {
        int len = emergency.length;
        int[] x = new int[len];
        for(int j=1;j<len+1;j++){
            int max=0;
            int idx=0;
            for(int i=0;i<len;i++){
                if(max<emergency[i]){
                    max = emergency[i];
                    idx = i;
                }
            }
            x[idx]=j;
            emergency[idx]=0;
        }
        return x;
    }
}