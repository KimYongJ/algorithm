class Solution {
    public int solution(int number, int limit, int power) {
        int s = 0;
        for(int i=1; i<=number; i++){
            int c = 0;
            for(int j=1; j*j<=i; j++){
                 if(j*j==i) c++;
                 else if(i%j==0) c+=2;
            }
            s+= c>limit ? power : c;
        }        
        return s;
    }
}