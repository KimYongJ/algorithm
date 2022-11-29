class Solution {
    public int solution(int n) {
        int data =0;
        for(int i=0;i<n+1;i+=2){
            data+=i;
        }
        return data;
    }
}