import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> list = new ArrayList<>();
        for(int x : arr)
            if(x%divisor==0)
                list.add(x);
        
        if(list.size()<1)
            return new int[]{-1};
        else{
            int[] result = new int[list.size()];
            
            for(int i=0; i<list.size(); i++)
                result[i] = list.get(i);
            
            Arrays.sort(result);
            
            return result;          
        }
    }
}