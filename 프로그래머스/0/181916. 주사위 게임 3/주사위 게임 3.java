import java.util.*;

class Solution {
    public int solution(int ... arr) {
        Arrays.sort(arr);
        int ans = 0;
        if (arr[0] == arr[3]) {
            ans = 1111 * arr[3];
        } else if (arr[0] == arr[2] || arr[1] == arr[3]) {
            ans = (int) Math.pow(arr[1] * 10 + (arr[0] + arr[3] - arr[1]), 2);
        } else if (arr[0] == arr[1] && arr[2] == arr[3]) {
            ans = (arr[0] + arr[3]) * (arr[3] - arr[0]);
        } else if (arr[0] == arr[1]) {
            ans = arr[2] * arr[3];
        } else if (arr[1] == arr[2]) {
            ans = arr[0] * arr[3];
        } else if (arr[2] == arr[3]) {
            ans = arr[0] * arr[1];
        } else {
            ans = arr[0];
        }

        return ans;
    }
}