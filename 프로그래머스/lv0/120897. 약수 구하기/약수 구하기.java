import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=(n+1)/2;i++){
            if(n%i==0){
                list.add(i);
            }
        }
        if(n!=1)
        list.add(n);
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}