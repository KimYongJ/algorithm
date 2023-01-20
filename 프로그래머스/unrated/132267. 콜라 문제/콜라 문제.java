// a = 5가져다주는빈병갯수  b = 3빈병에따라주는콜라갯수  n = 6내가갖고있는빈병갯수
class Solution {
    public int solution(int a, int b, int n) {
        int result = 0;
        
        while(a<=n){
            int my = (n/a)*b; // 내가 받는 병의 개수
            result += my;
            my += n%a; // 나머지 까지 더해줘야 내 병의 갯수가 완성된다.
            n = my;
        }
        return result;
    }
}