import java.util.ArrayDeque;
class Solution {
    
    private int result = 0;
    
    public int solution(int[] qu1, int[] qu2) {
        int max = qu1.length*3;// 종료조건
        long sum1=0; // 큐 1의 합
        long sum2=0; // 큐 2의 합
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        for(int i=0; i<qu1.length; i++){
            sum1+=qu1[i];
            sum2+=qu2[i];
            q1.add(qu1[i]); // 배열을 큐로 변환
            q2.add(qu2[i]); // 배열을 큐로 변환
        }

        long base = (sum1+sum2) / 2; // 기준 값
        while(base!=sum1){
            if(base>sum1){ // 기준 값보다 합계가 큰 큐의 값을 빼서 작은 큐에 넣는다.
                int num = q2.poll();
                sum1+=num;
                q1.add(num);
            }else{
                int num = q1.poll();
                sum1-=num;
                q2.add(num);
            }
            if(max<++result){
                result = -1;
                break;
            }
        }
        return result;        
    }
}