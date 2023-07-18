import java.util.HashMap;
class Solution {
    static int result = 1;
    public int solution(String[][] clothes) {
        HashMap<String,Integer> hm = new HashMap<>();
        
        for(String[] a : clothes)
            hm.put(a[1], hm.getOrDefault(a[1],0)+1);
        
        for(HashMap.Entry<String,Integer> data : hm.entrySet())
            result *= (data.getValue()+1);
        
        return --result;
    }
}