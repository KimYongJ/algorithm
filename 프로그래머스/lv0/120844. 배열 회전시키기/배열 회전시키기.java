import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(int[] n, String d) {
        int len = n.length;
        List<Integer> list = Arrays.stream(n).boxed().collect(Collectors.toList());
        if(d.equals("right")){
            list.add(0, n[len-1]);
            list.remove(len);
        }else{
            list.add(n[0]);
            list.remove(0);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}