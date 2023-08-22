import java.util.PriorityQueue;
import java.util.Collections;
class Solution {
    class DualPriority_Q{
        PriorityQueue<Integer> q = new PriorityQueue<>();
        PriorityQueue<Integer> mq = new PriorityQueue<>(Collections.reverseOrder());
        
        public void add(int num){ // 큐에 데이터 추가
                q.add(num);
                mq.add(num);
        }
        public int size(){
            return q.size();
        }
        public void remove(int num){ // 큐 데이터 삭제
            if(num==1){ // 최댓값삭제
                q.remove(mq.poll());
            }else if(num==-1){// 최솟값삭제
                mq.remove(q.poll());
            }
        }
        public int[] print(){ // 최대 최솟값 반환
            int max = 0;
            int min = 0;
            if(q.size()!=0){
                max = mq.poll();
                min = q.poll();
            }        
            return new int[]{max,min};
        }
    }

    public int[] solution(String[] operations) {
        DualPriority_Q q = new DualPriority_Q();
        for(String s : operations){
            char cmd = s.charAt(0);
            int num = Integer.parseInt(s.substring(2));
            if(cmd=='I'){// 큐에 데이터 Insert
                q.add(num);
            }else if(cmd=='D'){
                if(q.size()==0) continue;// 큐가 비어있다면 삭제 연산 무시
                q.remove(num);
            }
        }
        return q.print();

    }
}