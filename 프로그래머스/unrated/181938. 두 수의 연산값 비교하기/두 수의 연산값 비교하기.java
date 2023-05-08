class Solution {
    public int solution(int a, int b) {
        int x = Integer.parseInt(""+a+b);
        int y = 2*a*b;
        return x>y ? x : y;
    }
}