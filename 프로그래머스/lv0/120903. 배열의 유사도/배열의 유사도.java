import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[] s1, String[] s2) {
        int result =0;
        List<String> list = Arrays.stream(s2).collect(Collectors.toList());
        for(String x : s1)
            if(list.contains(x))result++;       
        return result;
    }
}