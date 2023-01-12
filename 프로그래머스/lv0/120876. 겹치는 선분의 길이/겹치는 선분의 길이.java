class Solution {
    public int solution(int[][] l) {
        int[] arr = new int[201];
        for(int i=0; i<3; i++) for(int j=l[i][0]; j<l[i][1]; j++)
                arr[j+100] += 1;

        int cnt = 0;
        
        for(int i=0; i<201; i++) if(arr[i]>1) cnt++;
        
        return cnt;
        
    }
}