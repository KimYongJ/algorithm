// https://github.com/KimYongJ/algorithm
import java.util.PriorityQueue;
class Solution {
    public int solution(int[] A, int[] B) {
        int result = 0;
        PriorityQueue<Integer> Aq = new PriorityQueue<>();
        PriorityQueue<Integer> Bq = new PriorityQueue<>();
        for(int i=0; i<A.length; i++){
            Aq.add(A[i]);
            Bq.add(B[i]);
        }
        while(!Bq.isEmpty()){
            int bNum = Bq.poll();
            if(bNum>Aq.peek()){ // B에서 가장 첫번째로 A보다 큰 숫자일 경우 결과에 +1;
                Aq.poll();
                result++;
            }
        }
        return result;
    }
}