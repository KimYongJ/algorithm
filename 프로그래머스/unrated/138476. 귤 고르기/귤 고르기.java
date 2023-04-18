// https://github.com/KimYongJ
import java.util.Arrays;
class Solution {
    static int MAX = 10000000;
    public int solution(int k, int[] t) {
        int n =0;
        int[] cnt = new int[MAX+1];

        for(int num : t)
            cnt[num]++;
        
        Arrays.sort(cnt);
        
        for(int i=MAX; i>=0; i--){
            k -= cnt[i];
            if(k<1) {
                n = MAX-i+1;
                break;
            }
        }
        return n;
    }
}