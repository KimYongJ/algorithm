// https://github.com/KimYongJ
import java.util.HashSet;
class Solution {
    public int solution(int[] topping) {
        int cnt = 0;
        int[] a = dp(topping, true);
        int[] b = dp(topping, false);
        for(int i=0; i<topping.length-1; i++)
            if(a[i]==b[i+1])
                cnt++;
        
        return cnt;
    }
    public int[] dp(int[] topping, boolean tf){
        int len = topping.length-1;
        
        int[] dp = new int[len+1];
        
        HashSet<Integer> hs = new HashSet<>();
        for(int i=0; i<topping.length; i++){
            if(tf){
                hs.add(topping[i]);
                dp[i] = hs.size();
            }
            else{
                hs.add(topping[len]);
                dp[len] = hs.size();
                len--;
            }
        }
        return dp;
    }
}