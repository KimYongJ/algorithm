class Solution {
    public int[] solution(int n, int k) {
        int[] result = new int[n/k];
        for(int i=0;i<n/k; i++)
            result[i] = k*(i+1);
        return result;
    }
}