// https://github.com/KimYongJ
import java.util.HashSet;
class Solution {
    public int solution(int[] e) {
        HashSet<Integer> hs = new HashSet<>();
        int[] e1 = new int[e.length*2];
        
        for(int i=0; i<e.length; i++)
            e1[i] = e1[i+e.length] = e[i];
        
        for(int base=0; base<e.length; base++){
            for(int i=base; i<e.length+base; i++){
                int n = 0;            
                for(int j=base; j<i+1; j++){
                    n += e1[j];
                }
                hs.add(n);
            }
        }

        return hs.size();
    }
}