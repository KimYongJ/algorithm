
class Solution {
    public int solution(int[][] t) {
        for(int i=t.length-1; i>0; i--){
            for(int j=0;j<t[i].length-1;j++){
                t[i-1][j] += Math.max(t[i][j],t[i][j+1]);
            }
        }
        return t[0][0];
    }
}
// import java.util.Arrays;
// import java.util.Collections;
// class Solution {
//     public int solution(int[][] t) {
//         int len = t.length-1;
//         int[][] dp = new int[len+1][len+1];
//         dp[0][0] = t[0][0];
//         for(int i=0; i<len; i++){ // 삼각형 세로로 한칸씩 내려감
//             for(int j=0; j<t[i].length; j++){ //상하단을 더할 때 상단의 길이 만큼 반복
//                 for(int z=j;z<j+2; z++){ // 하단은 2번만 반복하면 되니까 상단의 가로 index에서 +2만큼 반복
//                     int sum = dp[i][j] + t[i+1][z]; // 상단에 지금까지 저장된 dp테이블과 아래 하단의 값을 더함
//                     if(dp[i+1][z]<sum) // dp저장된 값이 작다면 값 update
//                         dp[i+1][z]=sum;
//                 }
//             }
//         }
        
//         Arrays.sort(dp[len]); // 최종 값 정렬
        
//         return dp[len][dp[len].length-1]; // 가장 마지막 값 출력
//     }
// }