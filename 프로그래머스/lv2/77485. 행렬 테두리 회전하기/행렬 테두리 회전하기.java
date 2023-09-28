// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    public ArrayList<Integer> solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows+1][columns+1];
        ArrayList<Integer> result = new ArrayList<>();
        int idx = 1;
        for(int i=1; i<arr.length; i++){
            for(int j=1; j<arr[0].length; j++){
                arr[i][j] = idx++;
            }
        }
        
        for(int[] q: queries)
            rotation(q,arr,result);
        
        return result;
    }
    public void rotation(int[] q,int[][] arr, ArrayList<Integer> result){
        int min = Integer.MAX_VALUE; // 테두리에서 가장 최솟값 담을 변수
        int before = arr[q[0]+1][q[1]];
        int now = 0;
        for(int x = q[1]; x<=q[3]; x++){// 사각형의 윗변
            now = arr[q[0]][x];
            arr[q[0]][x] = before;
            before = now;
            min = Math.min(min,arr[q[0]][x]);
        }
        for(int y=q[0]+1; y<=q[2]; y++){ // 사각형의 우변
            now = arr[y][q[3]];
            arr[y][q[3]] = before;
            before = now;
            min = Math.min(min,arr[y][q[3]]);
        }
        for(int x=q[3]-1;x>=q[1]; x--){ // 사각형의 아랫변
            now = arr[q[2]][x];
            arr[q[2]][x] = before;
            before = now;
            min = Math.min(min,arr[q[2]][x]);
        }
        for(int y=q[2]-1; y>q[0]; y--){ // 사각형의 좌변
            now = arr[y][q[1]];
            arr[y][q[1]] = before;
            before = now;
            min = Math.min(min,arr[y][q[1]]);
        }
        result.add(Math.min(min,before));
    }
}
