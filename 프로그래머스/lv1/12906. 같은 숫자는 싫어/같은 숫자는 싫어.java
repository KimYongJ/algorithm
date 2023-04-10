import java.util.ArrayList;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int x = -1;
        
        for(int a : arr){
            if(x==a) continue;
            list.add(a);
            x = a;
        }
        int[] result = new int[list.size()];
        
        for(int i=0; i<list.size(); i++)
            result[i] = list.get(i);
        
        return result;
    }
}