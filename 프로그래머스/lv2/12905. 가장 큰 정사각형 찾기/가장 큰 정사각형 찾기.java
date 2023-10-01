// https://github.com/KimYongJ/algorithm
class Solution{
    public int solution(int[][] board){
        int result = 0;
        
        int y = board.length+1;
        int x = board[0].length+1;
        int[][] b = new int[y][x]; // 0을 패딩한 배열을 하나 선언
        
        for(int i=1; i<y; i++){ // 0을 패딩한 배열을 초기화 함
            for(int j=1; j<x; j++){
                
                b[i][j] = board[i-1][j-1]; // 해단 값을 넣는다.
                
                // 값을 넣음 과 동시에 DP를 진행한다.
                if(b[i][j]!=0){ // 넣은 값이 0이 아닐 경우 DP 실행
                    // 기준 숫자에서, 왼쪽, 위쪽, 왼위쪽 숫자 중 최소 숫자를 구해서+1을 한다. 수학 공식 같은 식이다.
                    b[i][j] = Math.min(b[i-1][j-1],Math.min(b[i-1][j],b[i][j-1]))+1;
                    result = Math.max(result,b[i][j]);
                }
            }
        }
        
        return result*result;
    }
}