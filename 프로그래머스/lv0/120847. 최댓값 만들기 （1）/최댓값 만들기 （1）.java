import java.util.*;
class Solution {
    public int solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        int len = numbers.length;
        for(int x : numbers){
            list.add(x);
        }
        System.out.println(1);
        Collections.sort(list);
        return list.get(len-1)*list.get(len-2);
    }
}