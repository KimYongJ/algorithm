import java.util.ArrayDeque;
class Solution {
    
    private int result = 0;
    
    public int solution(int[] qu1, int[] qu2) {
        ArrayDeque<Integer> q1 = new ArrayDeque<>();
        ArrayDeque<Integer> q2 = new ArrayDeque<>();
        int max = qu1.length*3;// 종료조건
        long sum1=0; // 큐 1의 합
        long sum2=0; // 큐 2의 합
        for(int i=0; i<qu1.length; i++){
            q1.add(qu1[i]); // 배열을 큐로 변환
            q2.add(qu2[i]); // 배열을 큐로 변환
            sum1+=qu1[i];
            sum2+=qu2[i];
        }

        long base = (sum1+sum2) / 2; // 기준 값
        while(--max>0){
            if(base==sum1) 
                break;
            if(base>sum1){ // 기준 값보다 합계가 큰 큐의 값을 빼서 작은 큐에 넣는다.
                int num = q2.poll();
                sum1+=num;
                q1.add(num);
            }else{
                int num = q1.poll();
                sum1-=num;
                q2.add(num);
            }
            result++;
        }
        return max==0 ? -1 : result;        
    }
}