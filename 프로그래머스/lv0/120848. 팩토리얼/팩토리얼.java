class Solution {
    private int fac(int n){
        if(n==0){
            return 1;
        }
        return n*fac(n-1);
    }
    public int solution(int n) {
        int data=0;
        int result =0;
        while(n>=data){
            data = fac(++result);
        }
        return --result;
    }
}