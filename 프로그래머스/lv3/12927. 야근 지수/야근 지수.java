//https://github.com/KimYongJ/algorithm/
import java.util.PriorityQueue;
class Solution {
    
    private long result = 0;
    
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);// 높은 우선순위가 큰 수인 우선순위 큐 선언
        
        for(int w : works) 
            q.add(w);// 큐에 모든 값 insert
        
        for(int i=0; i<n; i++){
            int qData = q.poll();
            if(--qData<0)
                return 0;
            q.add(qData); // 큰 수 들을 뽑아서 -1 한 후 다시 insert
        }
        
        while(!q.isEmpty())
            result += Math.pow(q.poll(),2);
        
        
        return result;
    }
}