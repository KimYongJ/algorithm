import java.util.Arrays;
class Solution
{
    public int solution(int []A, int []B)
    {
        Arrays.sort(A);
        Arrays.sort(B);
        int n = 0,len = A.length;
        for(int i=0; i<len; i++)
            n += A[i] * B[len-1-i];
        
        return n;
    }
}