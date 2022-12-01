class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        int len = num2+1-num1;
        int[] result = new int[len];
        for(int i=0;i<len;i++){
            result[i]=numbers[num1++];
        }
        return result;
        
    }
}