class Solution {
    public int solution(int n) {
        String three = "";
        
        while(n>0){
            three += n%3;
            n /= 3;
        }

        return Integer.parseInt(three,3);
    }
}
