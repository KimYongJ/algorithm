class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] arr = new int[n+1][m+1];
        for(int[] p : puddles){
            arr[p[1]][p[0]] = -1; // arr에 물웅덩이는 -1로 세팅
        }
        arr[0][1] = 1;
        
        // 점화식 : arr[i][j] = arr[i][j-1] + arr[i-1][j]
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                if(arr[i][j]==-1) continue;
                int left = arr[i][j-1] == -1 ? 0 : arr[i][j-1];
                int uppe = arr[i-1][j] == -1 ? 0 : arr[i-1][j];
                arr[i][j] = (left+uppe)%1_000_000_007;
            }
        }
        
        return arr[n][m];
    }
}