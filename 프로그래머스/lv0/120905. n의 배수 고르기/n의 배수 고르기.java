import java.util.List;
import java.util.ArrayList;
class Solution {
    public int[] solution(int n, int[] numlist) {
        List<Integer> r = new ArrayList<Integer>();
        for(int i=0;i<numlist.length;i++)
            if(numlist[i]%n==0) r.add(numlist[i]);
        
        return r.stream().mapToInt(i->i).toArray();
    }
}