class Solution {
    static int result;
    public int solution(int n) {
        int len = Integer.bitCount(n);
        for(int i=n+1; i<1000001; i++){
            if(len == Integer.bitCount(i)){
                result = i;
                break;
            }
        }
        return result;
    }
}