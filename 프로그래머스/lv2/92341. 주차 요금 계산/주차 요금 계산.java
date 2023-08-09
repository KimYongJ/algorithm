import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String,Integer> treeMap = new TreeMap<>(); 
        for(String s : records){
            String[] part = s.split(" ");       // [시간 , 차량번호 , in out]을 분리
            String[] time = part[0].split(":"); // 시간과 분을 나눠 저장
            int min = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);//분으로 계산
            int type = part[2].equals("IN") ? -1 : 1;
            treeMap.put(part[1],treeMap.getOrDefault(part[1],0) + min*type);
        }
        
        int[] result = new int[treeMap.size()];
        int idx = 0;
        for(Map.Entry<String,Integer> entry : treeMap.entrySet()){
            int stayTime = entry.getValue();
            if(stayTime<1) stayTime += 1439;
            int pay = fees[1]; // 납부할돈
            if(stayTime>fees[0]){// 기본시간 초과일 경우
                pay += (int)Math.ceil(((stayTime-fees[0])/(double)fees[2])) * fees[3];// 기본시간 초과일 경우 돈 계산
            }
            result[idx++] = pay;
        }
        return result;
        
    }
}