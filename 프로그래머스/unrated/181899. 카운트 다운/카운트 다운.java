class Solution {
    public int[] solution(int start, int end_num) {
        int len = start-end_num+1;
        int[] result = new int[len];
        for(int i=0; i<len; i++){
            result[i] = start--;
        }
        return result;
    }
}