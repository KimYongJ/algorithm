import java.util.HashMap;
class Solution {
    public int solution(String[] strArr) {
        HashMap<Integer, Integer> m = new HashMap<>();
        int answer = 0;
        
        for(String str : strArr)
        {
            int len = str.length();
            int cnt = m.getOrDefault(len,0)+1;
            m.put(len, cnt);
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
}