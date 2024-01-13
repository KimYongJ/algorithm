import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr, int[][] intervals) {
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int[] inter : intervals)
            for(int i=inter[0]; i<=inter[1]; i++)
                list.add(arr[i]);
        
        return list;
    }
}