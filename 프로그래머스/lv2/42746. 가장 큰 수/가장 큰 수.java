// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
import java.util.Comparator;
class Solution {
    public String solution(int[] numbers) {
        Integer[] arr = new Integer[numbers.length];
        for(int i=0; i<numbers.length; i++)
            arr[i] = numbers[i];
        Arrays.sort(arr, new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b){
                if(a==0) return 1;
                if(b==0) return -1;
                if(a==b) return 0;
                int alen = (int)(Math.log10(a)+1);
                int blen = (int)(Math.log10(b)+1);
              //  System.out.println(alen +" "+blen);
                int sum1 = ((int)a*(int)Math.pow(10,blen)) + b; // a+b
                int sum2 = ((int)b*(int)Math.pow(10,alen)) + a; // b+a
                return sum2-sum1;
               // return 0;
            }
        }); 
        
        
        if(arr[0]==0)  // numbers에 0만 여러개 있을 경우
            return "0";
        
        StringBuilder sb = new StringBuilder();
        
        for(int s : arr){
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