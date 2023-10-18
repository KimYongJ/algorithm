// https://github.com/KimYongJ/algorithm
import java.util.Collections;
import java.util.ArrayList;
class Solution {
    ArrayList<Integer> list = new ArrayList<>();
    boolean[] visit;
    public int solution(int[] cards) {
        visit = new boolean[cards.length+1];
        
        for(int i=0; i<cards.length; i++)
            list.add( recursion(cards,i,0) );
        
        Collections.sort(list,(a,b)->b-a);
        
        return list.size()<=1 ? 0 : list.get(0) * list.get(1);
    }
    public int recursion(int[] cards, int idx, int cnt){
        if(!visit[idx]){
            visit[idx] = true;
            return idx == cards[idx]-1 ? cnt+1 : recursion(cards,cards[idx]-1,cnt+1);
        }else{
            return cnt; // 순환 참조일 경우 cnt에 +1을 하지 않고 저장한다.
        }
    }
}