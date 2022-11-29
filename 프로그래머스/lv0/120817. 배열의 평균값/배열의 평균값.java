class Solution {
    public double solution(int[] numbers) {
        int len = numbers.length;
        for(int i=1;i<len;i++)numbers[0]+=numbers[i];
        return (double)numbers[0]/len;
    }
}