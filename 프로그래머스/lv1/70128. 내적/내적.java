class Solution {
    public int solution(int[] a, int[] b) {
        a[0] = a[0]*b[0];
        for(int i=1; i<a.length; i++)
            a[0] += a[i]*b[i];
        return a[0];
    }
}