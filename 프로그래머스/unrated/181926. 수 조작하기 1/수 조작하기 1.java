class Solution {
    public int solution(int n, String control) {
        for(char c : control.toCharArray()){
            if(c=='w') n++;
            else if(c=='s') n--;
            else if(c=='d')n+=10;
            else n-= 10;
        }
        return n;
    }
}