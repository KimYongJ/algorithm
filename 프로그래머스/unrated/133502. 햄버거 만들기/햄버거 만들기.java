import java.util.*;
class Solution {
    public int solution(int[] arr) {
        int cnt = 0;
        int[] stack = new int[arr.length];
        int idx = 0;
        for(int x : arr){
            stack[idx] = x;
            if(idx>=3){
                if(stack[idx]==1 && stack[idx-1]==3 && stack[idx-2]==2 && stack[idx-3]==1){
                    cnt++;
                    idx-=4;
                }
            }
            idx++;
        }
        return cnt;
    }
}