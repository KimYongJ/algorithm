class Solution {
    public int solution(int number, int limit, int power) {
        int c = 0, s = 0;
        for(int i=0; i<number; i++){
            c = measure(i+1);
            if(c>limit)
                c=power;
            s+=c;
        }        
        return s;
    }
    public int measure(int m){
        int c = 0;
        for(int i=1; i<=m/2; i++)
            if(m%i==0)
                c++;
        return ++c;
    }
}