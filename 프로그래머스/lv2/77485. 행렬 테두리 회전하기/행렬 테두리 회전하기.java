// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
class Solution {
    
    private ArrayList<Integer> result;
    
    public ArrayList<Integer> solution(int rows, int columns, int[][] queries) {
        int[][] arr = new int[rows+1][columns+1];
        result = new ArrayList<>();
        int idx = 1;
        for(int i=1; i<arr.length; i++){
            for(int j=1; j<arr[0].length; j++){
                arr[i][j] = idx++;
            }
        }
        
        for(int[] q: queries)
            rotation(q,arr);
        
        return result;
    }
    public void rotation(int[] q,int[][] arr){
        ArrayList<int[]> coordinate = new ArrayList<>();// 테두리 좌표 값들을 순차적으로 담는다.
        ArrayList<Integer> nlist = new ArrayList<>(); // 테두리 값들을 순차적으로 담는다.
        int min = Integer.MAX_VALUE; // 테두리에서 가장 최솟값 담을 변수
        int x = q[1];
        int y = q[0];
        for(; x<=q[3]; x++){
            coordinate.add(new int[]{y,x}); // 사각형의 윗변 좌표를 담는다.
            nlist.add(arr[y][x]);
            min = Math.min(min,arr[y][x]);
        }
        x--;
        for(y=y+1; y<=q[2]; y++){
            coordinate.add(new int[]{y,x}); // 우변 좌표를 담는다.
            nlist.add(arr[y][x]);
            min = Math.min(min,arr[y][x]);
        }
        y--;
        for(x=x-1;x>=q[1]; x--){
            coordinate.add(new int[]{y,x}); // 하변 좌표를 담는다.
            nlist.add(arr[y][x]);
            min = Math.min(min,arr[y][x]);
        }
        x++;
        for(y=y-1; y>q[0]; y--){
            coordinate.add(new int[]{y,x}); // 좌변 좌표를 담는다.
            nlist.add(arr[y][x]);
            min = Math.min(min,arr[y][x]);
        }
        for(int i=1; i<coordinate.size(); i++){
            int[] p = coordinate.get(i);
            arr[p[0]][p[1]] = nlist.get(i-1);
        }
        int[] p = coordinate.get(0);
        arr[p[0]][p[1]] = nlist.get(nlist.size()-1);
        
        result.add(min);
    }
}