// https://github.com/KimYongJ/algorithm
import java.util.ArrayDeque;
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
        ArrayDeque<Integer> deq = new ArrayDeque<>();
        int min = Integer.MAX_VALUE; // 테두리에서 가장 최솟값 담을 변수
        deq.add(arr[q[0]+1][q[1]]);
        for(int i = q[1]; i<=q[3]; i++){// 사각형의 윗변
            deq.add(arr[q[0]][i]);
            arr[q[0]][i] = deq.poll();
            min = Math.min(min,arr[q[0]][i]);
        }
        for(int y=q[0]+1; y<=q[2]; y++){ // 사각형의 우변
            deq.add(arr[y][q[3]]);
            arr[y][q[3]] = deq.poll();
            min = Math.min(min,arr[y][q[3]]);
        }
        for(int x=q[3]-1;x>=q[1]; x--){ // 사각형의 아랫변
            deq.add(arr[q[2]][x]);
            arr[q[2]][x] = deq.poll();
            min = Math.min(min,arr[q[2]][x]);
        }
        for(int y=q[2]-1; y>q[0]; y--){ // 사각형의 좌변
            deq.add(arr[y][q[1]]);
            arr[y][q[1]] = deq.poll();
            min = Math.min(min,arr[y][q[1]]);
        }
        result.add(Math.min(min,deq.poll()));
    }
}