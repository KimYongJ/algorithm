// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);// 적의 수를 내림차순으로 담을 큐 선언
        int idx = 0; // 적군 인덱스
        for(; idx<enemy.length; idx++){
            n-=enemy[idx];
            pq.add(enemy[idx]);
            if(n<0){ // 현재 n의 값이 0인것도 포함시키면 안된다. 0보다 작을 때만 무적권을 써야한다.
                if(k>0){
                    k--;
                    n+=pq.poll();
                }else{
                    break;
                }
            }
        }
        return idx;
    }
}