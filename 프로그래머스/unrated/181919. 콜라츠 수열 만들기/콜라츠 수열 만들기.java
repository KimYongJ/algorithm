import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> solution(int n) {
        
        ArrayList<Integer> list = new ArrayList<>();
        
        while(list.add(n))
            if(n==1) break;
            else if(n%2==0) n/=2;
            else n = 3*n+1;
        
        return list;
    }
}