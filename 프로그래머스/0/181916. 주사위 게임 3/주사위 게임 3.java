import java.util.*;

class Solution {
    public int solution(int ... arr) {
        Set<Integer> set = new HashSet<>();
        int answer = 0, size = 0,
            cnt[] = new int[7];
        
        for(int a : arr){
            set.add(a);
            cnt[a]++;
        }
      
        size = set.size();
        if(size == 2){
            boolean flag = false;
            int pq[]=new int[2];
            int idx = 0;
            for(int i=1; i<7; i++){
                if(cnt[i] > 1){
                    pq[idx++] =i;
                    if(cnt[i] == 2)
                        flag = true;
                }else if(cnt[i] == 1){
                    pq[1] = i;
                }
            }
            if(flag) // 2개 2개가 같을 때 
            {
                answer = (pq[0]+pq[1]) * Math.abs(pq[0]-pq[1]);
            }else
            {
                answer = ((10*pq[0])+pq[1] )* ((10*pq[0])+pq[1]);
            }
        }
        else if(size == 3){
            answer = 1;
            for(int i=1; i<7; i++){
                if(cnt[i] == 1){
                    answer *= i;
                }
            }
        }
        else if(size == 1 || size == 4){
            for(int i=1; i<7; i++){
                if(cnt[i] != 0){
                    answer = i;
                    break;
                }
            }
            if(size == 1)
                answer *= 1111;
        }

        return answer;
    }
}