// hashMap으로 현재 값과, 다음 값을 저정한다.
import java.util.HashMap;
class Solution {
    static HashMap<Long,Long> hm = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] result = new long[len];
        for(int i=0; i<len; i++){
            result[i] = find(room_number[i]);
        }
        return result;
    }
    public long find(long n){
        if(!hm.containsKey(n)){
            hm.put(n,n+1);
            return n;
        }
        long nextRoom = hm.get(n);
        long emptyRoom = find(nextRoom);
        hm.put(n,emptyRoom);
        return emptyRoom;
    }
}