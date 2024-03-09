import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();
        int size = 0;
        for(int i=0; i<arr.length; i++)
            if(size == 0 || list.get(size-1) != arr[i]) 
            {
                list.add(arr[i]); 
                size++;
            }
            else if(list.get(size-1) == arr[i])
            {
                list.remove(size-1);
                size--;
            }
        
        if(size == 0)
            list.add(-1);
        return list;
    }
}