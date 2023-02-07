import java.util.*;
class Solution {
    public int[] solution(int[] arr, int[][] c) {
        int[] result = new int[c.length];
        
        for(int i=0; i<c.length; i++)
            result[i] = find(arr,c[i]);
        
        return result;
    }
    public int find(int[] arr, int[] c){
        int[] x = new int[c[1]-c[0]+1];
        int idx=0;
        for(int i=c[0]; i<=c[1]; i++)
            x[idx++] = arr[i-1];
        Arrays.sort(x);
        return x[c[2]-1];
    }
}