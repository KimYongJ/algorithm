class Solution {
    public int solution(int[] array, int height) {
        int cnt=0;
        for(int i=0;i<array.length;i++){
            if(height<array[i]) cnt++;
        }
        return cnt;
    }
}