import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public int[] solution(String my_string) {
        List<Integer> list = new ArrayList<>();
        for(char x : my_string.toCharArray()){
            if(Character.isDigit(x)){
                list.add(x-'0');
            }
        }
        Collections.sort(list);
        return list.stream().mapToInt(i->i).toArray();
    }
}