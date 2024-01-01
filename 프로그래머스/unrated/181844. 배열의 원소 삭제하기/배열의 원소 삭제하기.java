import java.util.ArrayList;
import java.util.HashSet;
class Solution {
    public ArrayList<Integer> solution(int[] arr, int[] delete_list) {
        ArrayList<Integer> result = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        for(int d : delete_list)
            set.add(d);
        
        for(int a : arr){
            if(!set.contains(a))
                result.add(a);
        }
        
        return result;
    }
}