import java.util.*;
class Solution {
    public int[] solution(int n) {
        LinkedHashSet<Integer> list = new LinkedHashSet<Integer>();
        int div =2;
        while(n>1){
            if(n%div==0){
                list.add(div);
                n /= div;
            }else
                ++div;
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}
