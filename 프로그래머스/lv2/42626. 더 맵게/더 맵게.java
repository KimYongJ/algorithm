import java.util.PriorityQueue;
class Solution {
    static int result;
    public int solution(int[] s, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int x : s)
            q.add(x);
        
        while(q.peek()<k){
            if(q.size()==1)
                return -1;
            q.add(q.poll() + q.poll()*2);
            result++;
        }
        return result;
    }
    
}