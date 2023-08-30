// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];
        
        for(int i=0; i<numbers.length; i++) arr[i] = String.valueOf(numbers[i]);
        
        Arrays.sort(arr,(o1,o2) -> (o2+o1).compareTo(o1+o2)); // 내림차순 정렬을 해야 하기 때문에 o2+o1부터 해준다.
        if(arr[0].equals("0"))  // numbers에 0이 여러개 있을 경우
            return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for(String s : arr){
            sb.append(s);
        }
        return sb.toString();
    }
}
// 메모리초과 
// class Solution {
//     String max = "0";
//     int length = 0;
//     boolean[] visit;
//     public String solution(int[] numbers) {
//         length = numbers.length;
//         visit = new boolean[length];
//         DFS(0,"0",numbers);
//         return max.substring(1);
//     }
//     public void DFS(int depth,String sum, int[] list){
//         if(length==depth){
//             if(sum.compareTo(max)>0){
//                 max = new String(sum);
//             }
//             return;
//         }
//         for(int i=0; i<length; i++){
//             if(!visit[i]){
//                 visit[i] = true;
//                 DFS(depth+1,sum+list[i],list);
//                 visit[i] =false;
//             }
//         }
//     }
// }