import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr) {
        ArrayList<Integer> stk = new ArrayList<>();
        int i = 0, len = arr.length;
        while(i < len)
        {
            if(stk.size() == 0 || (stk.size() > 0 && stk.get(stk.size()-1) < arr[i]))
                stk.add(arr[i++]);
            else if(stk.size() > 0 && stk.get(stk.size()-1) >= arr[i])
                stk.remove(stk.size() - 1);
        }
        return stk;
    }
}