// https://github.com/KimYongJ
// 규칙 : 짝수면 +1, 홀수면 가장 먼저나오는0을 1로바꿈, 0이 없는 홀수는 가장 맨 앞의 1을 0으로바꾸고 그앞에 1을 더함
class Solution {
    public long[] solution(long[] n) {
        for(int i=0; i<n.length; i++){
            
            long sum = 0;
            
            if(n[i]%2==0){ // 짝수
                sum = 1;
            }else{ // 홀수
                String base = Long.toBinaryString(n[i]);        // 2진수로 변경
                for(int j=base.length()-2; j>0; j--){
                    if(base.charAt(j)=='0'){
                        j = base.length()-1-j;
                        sum = (long)Math.pow(2,j)-(long)Math.pow(2,j-1);
                        break;
                    }
                }
                if(sum==0){
                    sum = (long)Math.pow(2,base.length())-(long)Math.pow(2,base.length()-1);    
                }
            }   
            n[i] += sum;
        }
        return n;
    }
}
// 아래코드 시간 초과
// class Solution {
//     public long[] solution(long[] n) {
//         for(int i=0; i<n.length; i++){
//             String base = Long.toBinaryString(n[i]);        // 2진수로 변경
//             while(check(Long.toBinaryString(++n[i]),base)); // 2개이하 다를 때까지 반복
//         }
//         return n;
//     }
//     public boolean check(String target, String base){
//         long len = target.length();
//         long baseLen = base.length();
//         base = new StringBuilder(base).reverse().toString();        // 문자열 뒤집기
//         target = new StringBuilder(target).reverse().toString();   // 문자열 뒤집기
//         int cnt = 0;
//         for(int i=0; i<len; i++){
//             if(i>=baseLen || base.charAt(i) != target.charAt(i)) cnt++;
//             if(cnt>2) return true;
//         }
//         return false;
//     }
// }