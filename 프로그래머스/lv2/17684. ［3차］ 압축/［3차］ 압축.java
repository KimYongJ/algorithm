import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
class Solution {
    static List<Integer> list = new ArrayList<>();
    static HashMap<String,Integer> hm = new HashMap<>();
    
    public List<Integer> solution(String msg) {
        int maxValue = 27;
        StringBuilder sb = new StringBuilder();
        
        for(int i=65; i<91; i++){
            hm.put((char)(i)+"",i-64);// A-Z까지 초기화한다.
        }
        
        for(int index = 0; index<msg.length(); index++){
            String before = sb.toString();
            sb.append(msg.charAt(index));
            String after = sb.toString();
            if(!hm.containsKey(after)){
                hm.put(after,maxValue++); // 사전에 추가해준다.
                list.add(hm.get(before)); // 인덱스 출력해준다.
                sb.setLength(0); // 스트링 초기화
                sb.append(msg.charAt(index)); // 다음 연산을 위해 추가해준다.
            }
        }
        list.add(hm.get(sb.toString())); // 연산 마지막에 남은 string을 추가 출력해준다.
        return list;
    }
}