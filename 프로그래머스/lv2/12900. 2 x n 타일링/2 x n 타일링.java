// https://github.com/KimYongJ/algorithm
class Solution {
    public int solution(int n) {
        int a = 1;
        int b = 2;
        for(int i=3;i<=n; i++){
            int c = (a+b) % 1_000_000_007;
            a = b;
            b = c;
        }
        return b;
    }
}