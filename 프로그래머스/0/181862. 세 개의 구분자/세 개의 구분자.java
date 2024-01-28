import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String myStr) {
        ArrayList<String> answer = new ArrayList<>();
        
        for(String str : myStr.split("[abc]+"))
            if(str.length()>0)
                answer.add(str);
        
        if(answer.size()==0)
            answer.add("EMPTY");
        
        return answer;
    }
}