import java.util.HashMap;
import java.util.Map;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String,Integer> hmS = new HashMap<>();
        HashMap<Integer,String> hmI = new HashMap<>();
        for(int i=0; i < players.length; i++){
            hmS.put(players[i],i);
            hmI.put(i,players[i]);
        }
        
        for(String str : callings){
            int value = hmS.get(str);// value를 가져온다.
            String front = hmI.get(value-1); // 앞사람의 이름을 가져온다.
            
            hmS.put(str,value-1);
            hmS.put(front,value);
            hmI.put(value,front);
            hmI.put(value-1,str);
        }
        
        for(Map.Entry<String,Integer> entry : hmS.entrySet())
            players[entry.getValue()] = entry.getKey();
        
        return players;
    }
}