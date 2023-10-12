// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    private ArrayList<int[]> list = new ArrayList<>();
    
    public ArrayList<int[]> solution(int n) {
        
        hanoi(n,1,3,2);
        
        return list;
    }
    public void hanoi(int N, int start, int to, int via){
        if(N==1){
            move(start,to);
        }
        else{
            hanoi(N-1,start,via,to);
            move(start,to);
            hanoi(N-1,via,to,start);
        }
    }
    public void move(int start, int to){
        list.add(new int[]{start,to});
    }
}