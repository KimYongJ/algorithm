import java.util.PriorityQueue;
import java.util.Collections;
class Solution {
    static PriorityQueue<Integer> q = new PriorityQueue<>();
    static PriorityQueue<Integer> mq = new PriorityQueue<>(Collections.reverseOrder());
    public int[] solution(String[] operations) {
        for(String s : operations){
            char cmd = s.charAt(0);
            int num = Integer.parseInt(s.substring(2));
            if(cmd=='I'){// 큐에 데이터 Insert
                q.add(num);
                mq.add(num);
            }else if(cmd=='D'){
                if(q.size()==0) continue;// 큐가 비어있다면 삭제 연산 무시
                if(num==1){ // 최댓값삭제
                    q.remove(mq.poll());
                }else if(num==-1){// 최솟값삭제
                    mq.remove(q.poll());
                }
            }
        }
        int max = 0;
        int min = 0;
        if(q.size()!=0){
            max = mq.poll();
            min = q.poll();
        }        
        return new int[]{max,min};// 최대, 최소 값 리턴
    }
}