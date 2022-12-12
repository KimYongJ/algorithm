import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public String solution(String my_string) {
       return Arrays.stream(my_string.split("")).distinct().collect(Collectors.joining());
    }
}

