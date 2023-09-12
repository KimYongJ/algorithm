import java.util.PriorityQueue;
class Solution {
    
    private long result = 0;
    
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->b-a);// 높은 우선순위가 큰 수인 우선순위 큐 선언
        
        for(int w : works) 
            q.add(w);// 큐에 모든 값 insert
        
        for(int i=0; i<n; i++){
            q.add(q.poll()-1); // 큰 수 들을 뽑아서 -1 한 후 다시 insert
        }
        if(q.peek()<1)// 가장 우선순위가 높은것이 1보다 작으면 return 0;
            return 0;
        
        int len = q.size(); // 큐의 크기대로 반복문 실행
        for(int i=0; i<len; i++){
            result += Math.pow(q.poll(),2);
        }
        
        return result;
    }
}