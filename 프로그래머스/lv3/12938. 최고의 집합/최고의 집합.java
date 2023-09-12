//https://github.com/KimYongJ
import java.util.Arrays;
class Solution {
    public int[] solution(int n, int s) {
        int[] result = new int[n]; // 결과를 반환할 배열 

        if(s/n==0)// 총합에서 n을 나눴을 때 몫이 0이라면 s를 만들 수 없기 때문에 -1 리턴
            return new int[]{-1};
        
        Arrays.fill(result,s/n);// 결과 배열의 각 원소들을 몫으로 저장한다.

        // 위에서 몫을 저장했으니 나머지를 통해 각 원소에 골고루 분배한다.그래야 곱햇을 때 가장 큰수가 나옴
        for(int i=0; i<s%n; i++){
            result[n-1-i]++;// 배열의 끝부분부터 나머지의 수만큼 +1을 해준다.
        }
        return result;
    }
}