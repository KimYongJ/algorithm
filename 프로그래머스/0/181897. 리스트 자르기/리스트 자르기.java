import java.util.Arrays;
class Solution {
    public int[] solution(int n, int[] slicer, int[] num_list) {
        int result[] = new int[(slicer[1] - slicer[0]) / slicer[2]+1];
        switch(n){
            case 1: result = Arrays.copyOf(num_list,slicer[1]+1);
                break;
            case 2: result = Arrays.copyOfRange(num_list,slicer[0],num_list.length);
                break;
            case 3: result = Arrays.copyOfRange(num_list,slicer[0],slicer[1]+1);
                break;
            default:
                int idx = 0;
                for(int i=slicer[0]; i<=slicer[1]; i+=slicer[2])
                    result[idx++] = num_list[i];
        }
        
        return result;
    }
}