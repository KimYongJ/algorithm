// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
import java.util.ArrayList;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data,(a,b)->{
            if(a[col-1]==b[col-1]){
                return b[0]-a[0];
            }
            return a[col-1]-b[col-1];
        });
            
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i=row_begin-1; i<=row_end-1; i++){
            int sum = 0;
            for(int j=0; j<data[i].length; j++){
                sum += data[i][j] % (i+1);
            }
            list.add(sum);
        }
        
        int result = list.get(0);
        for(int i=1;i<list.size(); i++){
            result = result ^ list.get(i);
        }
        
        return result;
    }
}