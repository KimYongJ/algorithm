//https://github.com/KimYongJ
class Solution {
    private int[] result = new int[2];  // 결과를 반환할 배열
    private int[][] global;             // 재귀를 사용함으로 코드를 줄이기 위해 arr을 담을 global 2차원 배열 선언
    public int[] solution(int[][] arr) {
        global = arr;                   // 멤버 배열에 대입
        
        quad(arr.length,0,0);           // 쿼드 재귀 실행
        
        return result;
    }
    public void quad(int len , int x, int y){
        int base = global[x][y];        // 기준이 되는 숫자 base를 구함
        if(allSame(len,x,y)){           // 모든 변수가 같은지 체크
            result[base]++;             // 모든 변수가 같다면 base를 index로 하여 결과에 +1을 해준다.
        }else{                          // 모든 변수가 같지 않다면 아래 에 대해 재귀 실행
            int hlen = len/2;           // 길이를 반으로 나눈다.
            quad(hlen,x+hlen,y);        // 1사분면
            quad(hlen,x,y);             // 2사분면
            quad(hlen,x,y+hlen);        // 3사분면
            quad(hlen,x+hlen,y+hlen);   // 4사분면
        }
    }
    public boolean allSame(int len, int x, int y){
        int base = global[x][y];        // 기준이되는 숫자 base를 구함
        for(int i=x; i<x+len; i++){     // x좌표 부터 x+len 까지 반복
            for(int j=y; j<y+len; j++){ // y좌표 부터 y+len 까지 반복
                if(base!=global[i][j])  // 기준이 되는 숫자와 하나라도 다르면 false 리턴
                    return false;
            }
        }
        return true;
    }
    
    
}