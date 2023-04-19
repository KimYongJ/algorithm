// https://github.com/KimYongJ
import java.util.HashMap;
class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        HashMap<String,Integer> hm = new HashMap<>();
        int[] answer = new int[photo.length];
        
        for(int i=0; i<name.length; i++)
            hm.put(name[i],yearning[i]);
        
        int idx = 0;
        for(String[] pname: photo){
            int cnt = 0;
            for(String str : pname)
                cnt += hm.getOrDefault(str,0);
            answer[idx++] = cnt;
        }
        return answer;
    }
}