class Solution {
    public int solution(int a, int b, int c) {
        int x = 0;
        if(a==b && b==c && a==c){
            x = (a+b+c)*(a*a+b*b+c*c)*(a*a*a+b*b*b+c*c*c);
        }
        else if(a!=b && b!=c && a!=c){
            x = a+b+c;
        }else{
            x = (a+b+c) * (a*a+b*b+c*c);
        }
        return x;
    }
}