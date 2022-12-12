class Solution {

    public int solution(int n) {
        int data =1;
        for(int i=1;i<=11;i++){
            data*=i;
            if(n<data) return --i;
        }
        return data;
    }
}
