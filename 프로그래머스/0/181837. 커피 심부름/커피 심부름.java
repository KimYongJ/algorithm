import java.util.HashSet;
class Solution {
    public int solution(String[] order) {
        int answer = 0;
        HashSet<String> cf = new HashSet<String>(){{
            add("icecafelatte");add("cafelatteice");
            add("hotcafelatte");add("cafelattehot");add("cafelatte");
        }};
        for(String str : order)
            answer += cf.contains(str) ? 5000 : 4500;
        return answer;
    }
}