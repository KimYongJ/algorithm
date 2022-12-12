class Solution {

    public int solution(int n) {
        int data =1;
        for(int i=1;i<=10;i++){
            data*=i;
            if(n<data) return --i;
            else if(n==data) return i;
        }
        return 1;
    }
}