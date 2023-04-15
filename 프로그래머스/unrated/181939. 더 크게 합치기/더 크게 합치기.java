class Solution {
    public int solution(int a, int b) {
        int a1 = Integer.parseInt(a+""+b);
        int b1 = Integer.parseInt(b+""+a);
        return a1>b1 ? a1:b1;
    }
}