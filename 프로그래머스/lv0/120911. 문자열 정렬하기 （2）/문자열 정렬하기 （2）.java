import java.util.Arrays;
class Solution {
    public String solution(String my_string) {
        char[] array = my_string.toLowerCase().toCharArray();
        Arrays.sort(array);
        return new String(array);
    }
}