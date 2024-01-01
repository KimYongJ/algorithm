import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr, int[] delete_list) {
        ArrayList<Integer> result = new ArrayList<>();
        
        for(int a : arr)
            result.add(a);
        
        for(int d : delete_list)
            result.remove((Integer)d);
        
        return result;
    }
}