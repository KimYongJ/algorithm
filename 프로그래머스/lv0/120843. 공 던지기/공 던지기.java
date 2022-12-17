class Solution {
    public int solution(int[] numbers, int k) {
        int idx = 0;
        for(int i=1;i<k;i++){
            idx+=2;
            if(idx>numbers.length-1){
                idx = idx-numbers.length;
            }
        }
        return numbers[idx];
    }
}