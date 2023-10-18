// https://github.com/KimYongJ/algorithm
import java.util.Collections;
import java.util.ArrayList;
class Solution {
    ArrayList<Integer> list = new ArrayList<>();
    boolean[] visit;
    public int solution(int[] cards) {
        visit = new boolean[cards.length+1];
        for(int i=0; i<cards.length; i++){
            recursion(cards,i,0);
        }
        if(list.size()<=1){
            return 0;
        }
        Collections.sort(list,(a,b)->b-a);
        return list.get(0) * list.get(1);
    }
    public void recursion(int[] cards, int idx, int cnt){
        if(!visit[idx]){
            visit[idx] = true;
            if(idx== cards[idx]-1){
                list.add(cnt+1);
                return;
            }
            recursion(cards,cards[idx]-1,cnt+1);
        }else{
            list.add(cnt);
        }
    }
}