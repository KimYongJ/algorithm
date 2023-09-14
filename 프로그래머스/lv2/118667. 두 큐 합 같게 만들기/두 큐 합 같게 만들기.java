import java.util.ArrayDeque;
class Solution {
    
    private int result = 0;
    
    public int solution(int[] qu1, int[] qu2) {
        int max = qu1.length*3;
        long sum1 = getSum(qu1); // 큐 1의 합
        long sum2 = getSum(qu2); // 큐 2의 합
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        for(int i=0; i<qu1.length; i++){
            q1.add(qu1[i]); // 배열을 큐로 변환
            q2.add(qu2[i]); // 배열을 큐로 변환
        }

        long base = (sum1+sum2) / 2; // 기준 값
        while(sum1!=sum2){
            if(base>sum1){ // 기준 값보다 합계가 큰 큐의 값을 빼서 작은 큐에 넣는다.
                int num = q2.poll();
                sum2-=num;
                sum1+=num;
                q1.add(num);
            }else{
                int num = q1.poll();
                sum1-=num;
                sum2+=num;
                q2.add(num);
            }
            if(max<++result){
                result = -1;
                break;
            }
        }
        return result;        
    }
    public long getSum(int[] q){
        long sum = 0;
        for(int i=0; i<q.length; i++){
            sum+= q[i];
        }
        return sum;
    }
}