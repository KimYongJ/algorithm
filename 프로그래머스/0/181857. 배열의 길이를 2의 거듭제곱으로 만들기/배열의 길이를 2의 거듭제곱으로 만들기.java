import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr) {
        int alen = arr.length;
        int rlen = 1;
        while(rlen<alen)
            rlen *= 2;
        return Arrays.copyOf(arr, rlen);
    }
}