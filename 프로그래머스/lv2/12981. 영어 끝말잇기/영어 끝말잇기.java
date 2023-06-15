import java.util.Set;
import java.util.HashSet;
class Solution {
    public int[] solution(int n, String[] words) {
        Set<String> set = new HashSet<>();
        String before = words[0];
        set.add(before);
        for(int i=1; i<words.length; i++){
            char be = before.charAt(before.length()-1);
            char af = words[i].charAt(0);
            if(set.contains(words[i]) || be != af){
                return new int[]{(i%n)+1,i/n+1};
            }
            before = words[i];
            set.add(before);
        }
        return new int[]{0,0};
    }
}