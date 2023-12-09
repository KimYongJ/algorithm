import java.util.Arrays;
class Solution {
    public int[] solution(int[] num_list) {
        Arrays.sort(num_list);
        int answer[] = new int[num_list.length-5];
        System.arraycopy(num_list, 5, answer,0, num_list.length-5);
        return answer;
    }
}