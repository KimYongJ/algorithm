// https://github.com/KimYongJ
import java.util.HashSet;
class Solution {
    public int solution(int[] topping) {
        int cnt = 0;
        int[] a = new int[topping.length];
        int[] b = new int[topping.length];
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0; i<topping.length; i++){
            hs.add(topping[i]);
            a[i] = hs.size();
        }
        hs.clear();
        for(int i=topping.length-1; i>=0; i--){
            hs.add(topping[i]);
            b[i] = hs.size();
        }
        for(int i=0; i<topping.length-1; i++)
            if(a[i]==b[i+1])
                cnt++;
        
        return cnt;
    }
}