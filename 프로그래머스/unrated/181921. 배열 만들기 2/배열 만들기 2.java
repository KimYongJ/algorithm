import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
class Solution {
    public int[] solution(int l, int r) {
        List<Integer> list = new ArrayList<>();
        String str = "";
        boolean check = false;
        for(int i=l; i<=r; i++){
            str = i+"";
            check = true;
            for(char c : str.toCharArray()){
                if(c!='0' && c!='5'){
                    check = false;
                    break;
                }
            }
            if(check)
                list.add(i);
        }
        if(list.size()==0){
            return new int[]{-1};
        }
            
        Collections.sort(list);
        int[] result = new int[list.size()];
        for(int i=0;i<list.size(); i++){
            result[i] = list.get(i);
        }
        return result;
    }
}