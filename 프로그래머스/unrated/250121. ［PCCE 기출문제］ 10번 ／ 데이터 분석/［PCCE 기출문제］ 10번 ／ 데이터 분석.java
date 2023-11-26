// https://github.com/KimYongJ/algorithm
import java.util.ArrayList;
import java.util.Collections;
class Solution {

    ArrayList<int[]> list = new ArrayList<>();
    
    public ArrayList<int[]> solution(int[][] data, String ext, int val_ext, String sort_by) {
        // data에서 ext값이 val_ext보다 작은 것만 list에 담습니다.
        int type = getType(ext);
        
        for(int i=0; i<data.length; i++)
            if(data[i][type] < val_ext)
                list.add(data[i]); // 해당 값만 리스트에 담습니다. 
        
        // list에서 sort_by기준으로 오름차순 정렬 합니다. 
        int type1 = getType(sort_by);
        
        Collections.sort(list,(a,b)->a[type1]-b[type1]); // sort_by값을 기준으로 오름차순
        
        return list;
    }
    public int getType(String str){
        int type = 0;
        if(str.charAt(0)=='d') 
            type = 1;
        else if(str.charAt(0)=='m') 
            type = 2;
        else if(str.charAt(0)=='r')
            type = 3;
        return type;
    }
}