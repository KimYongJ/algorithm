// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    private ArrayList<int[]> list = new ArrayList<>();
    
    public ArrayList<?> solution(int n) {
        
        recursion(n,1,3,2);// 1번에서 3번으로 간다.
        
        return list;
    }
    public void recursion(int n, int one, int three, int two){
        if(n>0){
            recursion(n-1,one,two,three);// 1번에서 2번으로 간다.
            list.add(new int[]{one,three});
            recursion(n-1,two,three,one);// 2번에서 3번으로 간다.
        }
    }
}
