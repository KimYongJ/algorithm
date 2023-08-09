import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        HashMap<String,Integer> hm = new HashMap<>();           // 단순 in out을 통해 계산
        TreeMap<String,Integer> sumHashMap = new TreeMap<>();   // 최종 머문 시간을 계산하는 것, 자동정렬
        for(String s : records){
            String[] part = s.split(" ");       // [시간 , 차량번호 , in out]을 분리
            String[] time = part[0].split(":"); // 시간과 분을 나눠 저장
            String carNumber = part[1];         // 차량 번호 
            int min = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);//분으로 계산
            if(hm.containsKey(carNumber)){      //해시맵에 carNumber가 이미 들어가있으면 out할 차례
                min -= hm.get(carNumber);       // 출차시간 - 입차시간 이다.
                int resultMin = sumHashMap.getOrDefault(carNumber,0); // 최종 머문 시간 계산을 위해 기존 머문시간 가져옴
                sumHashMap.put(carNumber,resultMin+min); // 기존머문시간 + 방금 머문시간
                hm.remove(carNumber);           // 해시맵에서 해당 차량번호를 출차한다.
            }else{
                hm.put(carNumber,min);          // 해시맵에 carNumber가 없으면 입차 한다.
            }
        }
        // 해시멥에 남아있는 것은 마지막까지 출차가 안된 것이므로 23:59분 출차로 따로 계산 해준다.
        for(Map.Entry<String,Integer> entry: hm.entrySet()){
            String carNumber = entry.getKey(); // 차량번호
            int min = 1439 - entry.getValue(); // 23:59분 출차 - 입차시간
            int resultMin = sumHashMap.getOrDefault(carNumber,0); // 해당 차량 번호의 최종 머문시간
            sumHashMap.put(carNumber,resultMin + min); // 시간계산을 다시해서 최종 머문시간 다시 세팅해줌
        }
        int[] result = new int[sumHashMap.size()];
        int idx = 0;
        for(Map.Entry<String,Integer> entry : sumHashMap.entrySet()){
            int stayTime = entry.getValue();
            int pay = fees[1]; // 납부할돈
            if(stayTime>fees[0]){// 기본시간 초과일 경우
                pay += (int)Math.ceil(((stayTime-fees[0])/(double)fees[2])) * fees[3];// 기본시간 초과일 경우 돈 계산
            }
            result[idx++] = pay;
        }
        return result;
        
    }
}