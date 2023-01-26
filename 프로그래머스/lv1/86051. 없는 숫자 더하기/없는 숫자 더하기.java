class Solution {
    public int solution(int[] numbers) {
        int r = 45;
        for(int x : numbers)
            r-=x;
        return r;
    }
}