class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        int len = arr.length;
        for(int[] q : queries)
            for(int i=q[0]; i<=q[1]; i++)
                if(i%q[2]==0 && i<len)
                    arr[i]++;

        return arr;
    }
}