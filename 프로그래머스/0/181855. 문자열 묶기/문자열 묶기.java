
class Solution {
    public int solution(String[] strArr) {
        int result      = 0
            , len       = 0
            , count[]   = new int[31];
        
        for(String str : strArr)
        {
            len = str.length();
            count[len]++;
            result = Math.max(result, count[len]);
        }
        return result;
    }
}
// import java.util.HashMap;
// class Solution {
//     public int solution(String[] strArr) {
//         HashMap<Integer, Integer> m = new HashMap<>();
//         int answer = 0;
        
//         for(String str : strArr)
//         {
//             int len = str.length();
//             int cnt = m.getOrDefault(len,0)+1;
//             m.put(len, cnt);
//             answer = Math.max(answer, cnt);
//         }
//         return answer;
//     }
// }