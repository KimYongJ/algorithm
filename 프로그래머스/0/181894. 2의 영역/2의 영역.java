import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr) {
        int result[] = new int[]{-1} 
            , start = -1 
            , end = -1 
            , len = arr.length;
        
        for(int i=0; i<(len+1)/2; i++){
            if(start == -1 && arr[i] == 2){
                start = i;
            }
            
            if(end == -1 && arr[len-1-i] == 2){
                end = len-1-i;
            }
            if(start != -1 && end != -1){
                return  Arrays.copyOfRange(arr,start,end+1);
            }
        }

        if(start != -1 || end != -1)
            result[0] = 2;
        
        return result;
    }
}