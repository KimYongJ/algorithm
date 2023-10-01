// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public long solution(int[] weights) {
        
        long result = 0;
        
        Arrays.sort(weights);
        
        for(int i=0; i<weights.length-1; i++){
            int a = weights[i];
            long same = 0; // 같은 번호가 몇개인지 체크
            long diff = 0; // 같은 번호를 제외하고 같은 토크 갯수를 구하기 위한 변수
            for(int j=i+1; j<weights.length; j++){
                int b = weights[j];
                if(a==b){
                    same++;
                }else{
                    if(a<<1 == b+b+b || a == b<<1 ||
                       a+a+a == b<<1 || a+a+a == b<<2 ||
                       a<<1 == b || a<<2 == b+b+b){
                        result++;
                        diff++;
                    }
                }
            }
            result+= same + (same*diff) + (same<2 ? 0 : fac(same-1));
            i+= same;
        }
        return result;
    }
    public long fac(long n){
        if(n==1) return 1;
        return n+fac(n-1);
    }
}