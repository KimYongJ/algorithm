import java.util.Arrays;
class Solution {
    public int[] solution(int[] p) {
        int len = p.length;
        int[] result = new int[len];
        for(int i=0; i<len; i++){
            for(int j=i+1; j<len; j++){
                result[i]++;
                if(p[i]>p[j])break;
            }
        }
        return result;
    }
}