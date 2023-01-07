class Solution {
    public int[] solution(int repeat, int total) {
        int[] result = new int[repeat];
        int start = total/repeat-repeat/2 + (repeat+1)%2;

        for(int i=0;i<repeat;i++)
            result[i] = start++;
        
        return result;
    }
}