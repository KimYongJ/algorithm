import java.util.*;
class Solution {
    public int[] solution(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1;i<n+1;i+=2){
            list.add(i);
        }
        return list.stream().mapToInt(i->i).toArray();
    }
}