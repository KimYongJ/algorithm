import java.util.Arrays;
class Solution {
    public int solution(int[] numbers) {
        int len = numbers.length;
        Arrays.sort(numbers);
        int plus = numbers[len-1]*numbers[len-2];
        int minus = numbers[0]*numbers[1];
        return plus>minus ? plus : minus;
    }
}