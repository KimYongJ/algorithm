class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int len1 = arr1.length;
        int len2 = arr1[0].length;

        for(int i=0; i<len1; i++)
            for(int j=0; j<len2; j++)
                arr1[i][j]+=arr2[i][j];

        return arr1;
    }
}