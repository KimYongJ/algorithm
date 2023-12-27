import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int a : arr)
            for(int i=0; i<a; i++)
                list.add(a);
        
        return list;
    }
}