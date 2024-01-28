import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String myStr) {
        ArrayList<String> answer = new ArrayList<>();
        
        for(String str1 : myStr.split("a"))
            for(String str2 : str1.split("b"))
                for(String str3 : str2.split("c"))
                    if(str3.length()>0)answer.add(str3);
        
        if(answer.size()==0)
            answer.add("EMPTY");
        
        return answer;
    }
}