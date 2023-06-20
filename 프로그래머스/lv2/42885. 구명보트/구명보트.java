import java.util.Arrays;
class Solution {
    public int solution(int[] people, int limit) {
        int sum = 0, result = 0;
        boolean backPlus = false; //  뒤에꺼더했는지 체크 더했다면 true
        int i = 0; // 앞에서부터 오는 변수
        int j = people.length;// 뒤에서부터 오는 변수
        
        Arrays.sort(people);

        while(i<j){
            if(!backPlus){
                sum = people[--j];
                backPlus = true;
                result++;
            }
            sum += people[i];
            if(limit<sum){
                backPlus = false;
                continue;
            }
            i++;
        }
        return result;
    }
}