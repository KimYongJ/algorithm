class Solution {
    public int solution(int[][] s) {
        // 가로에 큰 값을 넣음
        for(int i=0; i<s.length; i++)
            if(s[i][0]<s[i][1]){
                int tmp = s[i][0];
                s[i][0] = s[i][1];
                s[i][1] = tmp;
            }
        // 가로중 가장큰거, 세로중 가장큰거 곱함
        int max1=0, max2=0;
        for(int i=0; i<s.length; i++){
            max1 = s[i][0] > max1 ? s[i][0] : max1;
            max2 = s[i][1] > max2 ? s[i][1] : max2;
        }
        
        return max1*max2;
    }
}