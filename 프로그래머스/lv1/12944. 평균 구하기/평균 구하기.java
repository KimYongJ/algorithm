class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        for(int x : arr)
            answer += x;
        return answer/arr.length;
    }
}