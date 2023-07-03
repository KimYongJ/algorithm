class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int row1 = arr1.length;
        int col1 = arr1[0].length;
        int col2 = arr2[0].length;
        int[][] answer = new int[row1][col2];
        int[] dp = new int[row1*col2];
        // 행렬 왼쪽기준
        int idx = 0;
        for(int v=0; v<row1; v++){
            for(int z=0; z<col2; z++){
                int sum = 0;
                for(int y =0 ; y<col1; y++){
                    sum += arr1[v][y] * arr2[y][z];
                }
                dp[idx++] = sum;
            }
        }
        
        idx = 0;        
        for(int i=0; i<row1; i++){
            for(int j=0; j<col2; j++){
                answer[i][j] = dp[idx++];
            }
        }
        return answer;
    }
}