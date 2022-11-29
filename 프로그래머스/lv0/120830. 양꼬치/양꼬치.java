class Solution {
    public int solution(int n, int k) {
        int service = n/10;
        k-=service;
        return n*12000+k*2000;
        
    }
}