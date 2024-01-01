import java.util.Arrays;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        return Arrays.stream(arr)
            .filter(x ->  Arrays.stream(delete_list)
                    .noneMatch(y -> y == x))
            .toArray();
    }
}