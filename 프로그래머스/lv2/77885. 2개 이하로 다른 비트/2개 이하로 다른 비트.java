
class Solution {
    public long[] solution(long[] n) {
        for(int i=0; i<n.length; i++){
            
            long sum = 0; // 최종 계산에 더할 값
            
            if(n[i]%2==0){ // 짝수일 때
                sum = 1;
            }else{ // 홀수일 때
                String base = Long.toBinaryString(n[i]);        // 2진수로 변경
                for(int j=base.length()-2; j>0; j--){ // 2진수를 오른쪽에서 부터 탐색
                    if(base.charAt(j)=='0'){ // 2진수에서 0이 발견되면
                        j = base.length()-1-j; // 0이 발견된 index를 계산해서 j 변수에 담는다.
                        sum = (long)Math.pow(2,j)-(long)Math.pow(2,j-1); // 0이 발견된 인덱스를 1로 바꾸고 그전 인덱스를 0으로 바꾸는것, 더하고빼기로 계산가능
                        break;
                    }
                }
                if(sum==0){ // 모든 비트가 1일 때 sum은 0이게 되어 현재 이 if문을 타게 된다.
                    sum = (long)Math.pow(2,base.length())-(long)Math.pow(2,base.length()-1); 
                    // 가장큰 비트를 0으로 바꾸고 그앞에 비트 1을 붙여주는 것이다.
                }
            }   
            n[i] += sum;//계산한 sum을 최종값에 더해준다.
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