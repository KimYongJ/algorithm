// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public int solution(int[][] routes) {
        
        int answer = 1;
        
        Arrays.sort(routes,(a,b)->a[1]-b[1]); // 각 인자의 두번째, 첫번째 인자들 순으로 오름차순 정렬
        
        int position = routes[0][1]; // 그리디 기준 값 : 진출 지점

        for(int[] route : routes){
            if(position<route[0]){ // 그리디 기준 값 갱신조건 : 초기 진출 값보다 진입값이 크다면
                position = route[1];
                answer++;
            }
        }
        
        return answer;
    }
}