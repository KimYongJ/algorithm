
// 이분탐색 알고리즘 활용으로 문제를 해결
import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times); // 이분탐색은 정렬이되어있어야 한다.
        long ans = 0; // 결과담을 변수
        long left = 1; // 최소로 걸리는 시간 
        long right = (long)times[times.length-1] * n; // 최악으로 걸리는 시간 
        
        // times[i] * n은 걸리는 시간이라는 것을 고려해  걸리는시간/times[i]는 n이라는 것을 도출할 수 있다.
        while(true){
            if(left>right) break;// 탈출 조건 
            
            long mid = (left + right) / 2; // 최악으로 걸리는 시간과 최선으로 걸리는 시간을 이용해 중간시간을 만든다 
            long n1 = 0; // 몇명인지 구하는 것
            for(int time : times){
                n1 += mid/time; // 걸리는시간 = times[i]*n 이므로  n = 걸리는시간 / times[i]가 성립.
            }
            
            if(n1<n){// 위에서 구한 n1명이 n보다 작으면 왼쪽(left)의 값을 mid+1로한다.
                left = mid + 1;
                ans = left; // n1명이 n보다 작을 때 mid+1을 ans에 대입한다.
            }else{// 위에서 구한 n1명이 n보다 크면 n은 왼쪽에 있는 것이므로 mid-1을 한다. 
                right = mid - 1;
            }
        }
        
        
        return ans;
    }
}