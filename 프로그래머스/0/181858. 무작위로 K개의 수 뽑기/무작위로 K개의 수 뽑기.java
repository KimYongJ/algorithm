import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int a : arr)
        {
            if(!list.contains(a))
            {
                list.add(a);
                k--;
            }
            if(k==0) 
                break;
        }
        if(k!=0)
            for(int i=k; k>0; k--) 
                list.add(-1);
        
        return list;
    }
}