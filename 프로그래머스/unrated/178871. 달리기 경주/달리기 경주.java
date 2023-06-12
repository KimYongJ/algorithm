import java.util.HashMap;
import java.util.Map;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String,Integer> hm = new HashMap<>();
        
        for(int i=0; i<players.length; i++)
            hm.put(players[i],i);
        
        for(String call : callings){
            int idx = hm.get(call);
            
            if(idx>0){
                String tmp = players[idx-1];
                players[idx-1] = players[idx];
                players[idx] = tmp;
                
                hm.put(players[idx],idx-1);
                hm.put(players[idx-1],idx);
                
            }
        }
        return players;
    }
}