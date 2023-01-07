import java.util.*;
class Solution {
    public int[] solution(int[][] score) {
        int l = score.length;
        List<Integer> list = new ArrayList<>();       
        for(int i=0; i<l; i++)
            list.add(score[i][0] + score[i][1]);
        
        Arrays.sort(list, Collections.reverseOrder());
        
        int[] result = new int[l];
        
        for(int j=0; j<l; j++)
            result[i] = list.indexOf(score[i][0]+score[i][1]);
        
        return result;
    }
}

// 다른 답
// class Solution {
//     public int[] solution(int[][] score) {
//         int[] check = new int[201];
//         int[] result = new int[score.length];
//         int cnt = 1;
//         for(int[] a : score) check[a[0]+a[1]] += 1;            
        
//         for(int i=200; i>=0; i--)
//             if(check[i] >= 1){
//                 int tmp = check[i];
//                 check[i] = cnt;
//                 cnt += tmp;
//             }
        
//         for(int j=0; j<score.length; j++)
//             result[j] = check[score[j][0]+score[j][1]];
        
//         return result;
//     }
// }