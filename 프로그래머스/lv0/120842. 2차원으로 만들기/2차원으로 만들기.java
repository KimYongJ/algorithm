class Solution {
    public int[][] solution(int[] num_list, int n) {
        int[][] arr = new int[num_list.length/n][n];
        
        for(int i=0;i<num_list.length;i++){
            arr[i/n][i%n]=num_list[i];
        }
        
        return arr; 
    }
}