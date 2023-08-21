import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
class Solution {
    int result = 0;
    List<Character> list = new ArrayList<>();
    public int solution(String skill, String[] skill_trees) {
        for(char c : skill.toCharArray()) 
            list.add(c);
        
        loop : for(String s : skill_trees){
            int level = 0;
            Stack<Character> stack = new Stack<>();
            stack.push(skill.charAt(level));
            for(char c : s.toCharArray()){
                if(list.contains(c)){ // 스킬트리에 있는 값만 아래 실행
                    if(!stack.contains(c)) continue loop;
                    if(c == skill.charAt(level)){ // 현재 레밸과 같은거라면 level플러스
                        level++;
                        if(level== skill.length()) 
                            break;
                        stack.push(skill.charAt(level));
                    }
                }
            }
            result++;
        }
        return result;   
    }
}