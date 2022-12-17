import java.util.*;
class Solution {
    public int[] solution(int n) {
        HashSet<Integer> list = new HashSet<Integer>();
        int div =2;
        while(n!=0){
            if(n%div==0){
                list.add(div);
                n = div(div,n);
                if(n==1) break;
            }else
                ++div;
        }
        int[] result = list.stream().mapToInt(i->i).toArray();
        Arrays.sort(result);
        return result;
    }
    
    private static int div(int div,int n){
        while(n%div==0)
            n = n/div;
        return n;
    }
    
}