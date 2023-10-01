// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public int solution(int[] A, int[] B) {
        int result = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=0;i<B.length; i++){
            if(B[i]>A[result]){
                result++;
            }
        }
        
        return result;
    }
}
