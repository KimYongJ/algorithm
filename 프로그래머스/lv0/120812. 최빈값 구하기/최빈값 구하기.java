import java.util.*;

class Solution {
    public int solution(int[] array) {
        int result = 0, maxCount = 0;
        
        HashMap<Integer, Integer> hm = new HashMap<>();
        
        for(int x : array){
            int count = hm.getOrDefault(x,0)+1;
            if(maxCount < count){
                maxCount = count;
                result = x;
            }else if(count == maxCount)
                result = -1;
            hm.put(x,count);
        }
        return result;
    }
}
// class Solution {
//     public int solution(int[] array) {
//         int[] countingSort = new int[1001];
//         for(int x : array)
//             countingSort[x]++;
        
//         int max = 0, cnt = 0, result = 0;
//         for(int i=0; i<1001; i++)
//             if(max<countingSort[i]){ 
//                 max = countingSort[i];
//                 result = i;
//             }
        
//         for(int j=0; j<1001; j++){
//             if(max == countingSort[j]) cnt++;
//             if(cnt>1) return -1;
//         }
//         return result;
//     }
// }