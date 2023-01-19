// a = 5가져다주는빈병갯수  b = 3빈병에따라주는콜라갯수  n = 6내가갖고있는빈병갯수
class Solution {
    public int solution(int a, int b, int n) {
        int result = 0;
        
        while(a<=n){
            result += (n/a)*b;
            n =(n/a)*b + n%a;
        }
        return result;
    }
}