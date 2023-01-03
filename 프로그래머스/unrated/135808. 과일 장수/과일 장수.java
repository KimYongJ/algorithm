class Solution {
    public int solution(int k, int m, int[] score) {
        int[] s = new int[10];
        for(int k1 : score)
            s[k1]++;
        int sum = 0;
        int cnt = 0;
        while(k>=0){
            while(s[k]>0){
                s[k]--;
                cnt++;
                if(cnt==m){
                    sum += k * m;
                    cnt=0;
                }
            }
            if(s[k]==0)
                k--;
        }
        return sum;
    }
    
}