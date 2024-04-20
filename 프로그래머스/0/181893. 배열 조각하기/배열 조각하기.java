import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr, int[] q) {
        for(int i=0; i<q.length; i++){
            if(i%2==0){
                arr = Arrays.copyOfRange(arr, 0, q[i]+1);
            }else{
                arr = Arrays.copyOfRange(arr, q[i], arr.length);
            }
        }
        return arr;
    }
}