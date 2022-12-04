class Solution {
    public int solution(int n) {
        return Math.sqrt((double)n)%1 == 0 ? 1 : 2;
    }
}