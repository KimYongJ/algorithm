//https://github.com/KimYongJ/algorithm

import java.util.ArrayDeque;
class Solution {
    
    public int result = 0;
    
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        ArrayDeque<Integer> q = new ArrayDeque<>(); // 다리를 건너는 중인 트럭을 담을 큐
        int weightSum = 0;// 현재 다리를 건너는 중인 트럭의 무게의 총합을 구하는 변수
        int idx = 0; // 대기트럭 배열을 순회할 인덱스
        
        for(int i=0; i<bridge_length; i++){
            q.add(0); // 다리 길이 만큼 큐에 0을 넣는다.
        }
        
        while(idx<truck_weights.length){ // 대기트럭의 길이보다 인덱스가 작을 때만 반복

            int truck = q.poll(); // 큐에서 데이터를 하나 뽑아온다.
            
            if(truck != 0){ // 트럭의 무게 측정 0이 아닌 경우
                weightSum-= truck; // 다리를 건너는 중인 트럭에서 큐에서 뽑은 트럭의 무게를 뺀다
            }
            
            if(weightSum+truck_weights[idx] <= weight){// 다리를 건너는 중인 트럭 무게의 총합과 그다음 대기 중 트럭의 합이 다리가 견딜 수 있는 무게를 넘지 않는다면 이하 실행
                q.add(truck_weights[idx]); // 큐에 해당 트럭을 넣는다.
                weightSum+= truck_weights[idx]; // 다리를 건너는 중인 트럭 무게의 총합에 해당 트럭 무게를 더해준다.
                idx++; // 인덱스 추가 
            }else{
                q.add(0); // 견딜 수 있는 무게 초과시 0을 넣는다.
            }
            result++;
        }
        return result + q.size(); // 큐가 비어있지 않을 경우 큐의 크기만큼 결과에 더해준다. 큐 1개당 1초기 때문이다.
    }
}
