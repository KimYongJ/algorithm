class Solution {
    public int solution(int[] list, int n) {
        for(int x : list)
            if(x==n) return 1;
        return 0;
    }
}