import java.util.stream;
class Solution {
    public int solution(int[] arr1, int[] arr2) {
        int answer = Integer.compare(arr1.length, arr2.length);
        
        if(answer == 0){
            answer = Integer.compare(arr1.stream().sum() , arr2.stream().sum());
        }
        return answer;
    }
}