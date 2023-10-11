// https://github.com/KimYongJ/algorithm
import java.util.Arrays;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data,(a,b)->{
            if(a[col-1]==b[col-1]){
                return b[0]-a[0];
            }
            return a[col-1]-b[col-1];
        });
        int result = 0;
        for(int j=0; j<data[row_begin-1].length; j++){
            result += data[row_begin-1][j] % (row_begin-1+1);
        }    
        for(int i=row_begin; i<=row_end-1; i++){
            int sum = 0;
            for(int j=0; j<data[i].length; j++){
                sum += data[i][j] % (i+1);
            }
            result = result^sum;
        }
        
        return result;
    }
}