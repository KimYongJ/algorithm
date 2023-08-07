import java.util.List;
import java.util.ArrayList;
class Solution {
    public List<Integer> solution(int[] num_list, int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++)
            list.add(num_list[i]);
        return list;
    }
}