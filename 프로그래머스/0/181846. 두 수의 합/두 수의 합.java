import java.math.BigDecimal;
class Solution {
    public String solution(String a, String b) {
        return new BigDecimal(a).add(new BigDecimal(b)).toString();
    }
}