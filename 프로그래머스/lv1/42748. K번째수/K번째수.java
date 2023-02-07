import java.util.*;
class Solution {
    public int[] solution(int[] arr, int[][] c) {
        int[] result = new int[c.length];
        
        for(int i=0; i<c.length; i++){
            int[] copy = Arrays.copyOfRange(arr,c[i][0]-1,c[i][1]);
            Arrays.sort(copy);
            result[i] = copy[c[i][2]-1];
        }
        
        return result;
    }
}