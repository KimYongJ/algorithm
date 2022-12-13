class Solution {
    public int[][] solution(int[] num_list, int n) {
        int len = num_list.length/n;
        int[][] arr = new int[len][n];
        int cnt=0;
        for(int j=0;j<len;j++){
            for(int i=0;i<n;i++){
                arr[j][i]=num_list[cnt++];
            }
        }
        return arr; 
    }
}