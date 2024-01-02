import java.util.ArrayList;
class Solution {
    public ArrayList<String> solution(String[] todo_list, boolean[] finished) {
        ArrayList<String> result = new ArrayList<>();
        for(int i=0; i<finished.length; i++)
            if(!finished[i])
                result.add(todo_list[i]);
        
        return result;
    }
}