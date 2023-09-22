class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n][m];
        for(int[] p : puddles){
            arr[p[1]-1][p[0]-1] = -1; // arr에 물웅덩이는 -1로 세팅
        }
        
        for(int i=0; i<m; i++){ // 가로 방향 1세팅
            if(arr[0][i]==-1)break;
            arr[0][i] = 1;
        }
        for(int i=0; i<n; i++){// 세로 방향 1 세팅
            if(arr[i][0]==-1) break;
            arr[i][0] = 1;
        }
        
        // 점화식 : arr[i][j] = arr[i][j-1] + arr[i-1][j]
        for(int i=1; i<n; i++){
            for(int j=1; j<m; j++){
                if(arr[i][j]==-1) continue;
                int left = arr[i][j-1] == -1 ? 0 : arr[i][j-1];
                int uppe = arr[i-1][j] == -1 ? 0 : arr[i-1][j];
                arr[i][j] = (left+uppe)%1_000_000_007;
            }
        }
        
        
        return arr[n-1][m-1];
    }
}