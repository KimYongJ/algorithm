// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public int solution(int[][] A) {
        Arrays.sort(A,(a,b)->a[1]-b[1]);
        int answer = 1;
        int end = A[0][1];
        for(int i=1; i<A.length; i++){
            if(!(A[i][0]<end && end<=A[i][1])) {
                answer++;
                end = A[i][1];
            }
        }
        return answer;
    }
}