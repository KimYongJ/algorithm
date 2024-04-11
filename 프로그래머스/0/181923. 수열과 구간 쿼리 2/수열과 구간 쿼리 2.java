import java.util.ArrayList;
class Solution {
    final int MAX_VALUE = 1_000_001;
    public ArrayList<Integer> solution(int[] arr, int[][] queries) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int q[] : queries)
        {
            int num = MAX_VALUE;
            for(int i=q[0]; i<=q[1]; i++)
                if(arr[i] > q[2])
                    num = Math.min(num,arr[i]);

            list.add(num == MAX_VALUE ? -1 : num);
        }
        return list;
    }
}