import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public int[] solution(String my_string) {
  return Arrays.stream(my_string.replaceAll("[A-Z|a-z]", "").split("")).sorted().mapToInt(Integer::parseInt).toArray();
    }
}