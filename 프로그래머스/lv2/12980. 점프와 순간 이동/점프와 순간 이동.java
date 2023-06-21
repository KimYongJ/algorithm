public class Solution {
    static int result;
    public int solution(int n) {
        while(n!=0){
            if(n%2==1){
                result++;
                n--;
            }
            n/=2;
        }
        return result;
    }
}