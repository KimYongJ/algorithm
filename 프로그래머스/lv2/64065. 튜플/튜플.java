import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public int[] solution(String str) {
        HashMap<Integer,Integer> hm = new HashMap<>();
        String[] s = str.replaceAll("[\\{\\}]","").replaceAll(","," ").split(" ");
        for(int i=0; i<s.length; i++){
            int num = Integer.parseInt(s[i]);
            hm.put(num,hm.getOrDefault(num,0)+1);
        }
        
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(hm.entrySet());
        Collections.sort(list,(o1,o2)-> {return o2.getValue().compareTo(o1.getValue());});
        
        int[] result = new int[list.size()];
        for(int i=0; i<result.length; i++){
            result[i] = list.get(i).getKey();
        }
        return result;
    }
}