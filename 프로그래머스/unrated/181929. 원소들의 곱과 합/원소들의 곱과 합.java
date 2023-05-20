class Solution {
    public int solution(int[] num_list) {
        int sum = 0,  mul = 1;
        for(int x : num_list){
            sum += x;
            mul *= x;
        }
        return sum*sum > mul ? 1 : 0;
    }
}