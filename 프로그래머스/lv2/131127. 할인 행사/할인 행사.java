import java.util.HashMap;
class Solution {
    static int result,idx,len;
    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String,Integer> hm = new HashMap<>();
        // 반복할 횟수 10일이니까 discount의 배열에서 10을 빼고 +1
        len = discount.length-9;
        for(int i=0; i<len; i++){
            hm.clear();
            for(int x = idx; x<idx+10; x++){
                hm.put(discount[x],hm.getOrDefault(discount[x],0)+1);
            }
            idx++;
            // 해쉬맵의 값과 비교한다.
            boolean bool = true;
            for(int x = 0; x<want.length; x++)
                if(!hm.containsKey(want[x]) ||number[x] != hm.get(want[x]) ){
                    bool = false;
                    break;
                }
            
            if(bool) result++;
        }
        
        return result;
    }
}