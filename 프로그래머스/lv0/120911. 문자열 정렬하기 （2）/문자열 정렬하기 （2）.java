import java.util.Arrays;
class Solution {
    public String solution(String my_string) {
        char[] array = my_string.toCharArray();
        for(int i=0;i<array.length;i++){
            array[i]=Character.toLowerCase(array[i]);
        }
        Arrays.sort(array);
        return new String(array);
    }
}