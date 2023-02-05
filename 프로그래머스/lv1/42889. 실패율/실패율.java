// 스테이지 레벨이 주어진다.  각스테이지 별로 몇명이 있는지 헤아린다.
// 각 스테이지의 실패율을 fail에 담는다. 
import java.util.*;
class Solution {
    public int[] solution(int N, int[] s) {
        int[] count = new int[N+2];
        
        for(int i=0; i<s.length; i++)
                count[s[i]]++;
        
        Double[] fail = new Double[N];
        List<Double> list = new ArrayList<>();
        int num = s.length;
        for(int i=1; i<N+1; i++){
            fail[i-1] = count[i]!=0 ? (double)count[i] / num : 0.0;
            list.add(fail[i-1]);
            num -= count[i];
        }
        
        Arrays.sort(fail,Collections.reverseOrder());
        
        int[] result = new int[N];
        for(int i=0; i<N; i++){
            result[i] = list.indexOf(fail[i])+1;
            list.add(result[i]-1,-1.0);
            list.remove(result[i]);
        }
        return result;
    }
}