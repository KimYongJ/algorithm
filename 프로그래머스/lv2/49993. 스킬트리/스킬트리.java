import java.util.List;
import java.util.ArrayList;
class Solution {
    int result = 0;
    List<Character> list = new ArrayList<>();
    public int solution(String skill, String[] skill_trees) {
        for(char c : skill.toCharArray()) 
            list.add(c);
        
        loop : for(String s : skill_trees){
            int level = 0;
            List<Character> check = new ArrayList<>();
            check.add(skill.charAt(level));
            for(char c : s.toCharArray()){
                if(list.contains(c)){ // 스킬트리에 있는 값만 아래 실행
                    if(!check.contains(c)) continue loop;
                    if(c == skill.charAt(level)){ // 현재 레밸과 같은거라면 level플러스
                        level++;
                        if(level== skill.length())  // level이 마지막이라면 break문으로 결과에 +1이 되도록함
                            break;
                        check.add(skill.charAt(level));
                    }
                }
            }
            result++;
        }
        return result;   
    }
}