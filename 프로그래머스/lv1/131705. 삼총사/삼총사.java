class Solution {
    public int solution(int[] number) {
        int cnt = 0;
        for(int a=0; a<number.length-2; a++)
            for(int b=a+1; b<number.length-1; b++)
                for(int c=b+1; c<number.length; c++)
                    if(number[a]+number[b]+number[c] == 0) cnt++;
                    
        return cnt;
    }
}