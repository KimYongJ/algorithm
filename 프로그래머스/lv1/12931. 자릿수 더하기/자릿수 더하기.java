import java.util.*;

public class Solution {
    public int solution(int n) {
        int result = 0;
        for(char c : String.valueOf(n).toCharArray())
            result += c-48;
        return result;
    }
}