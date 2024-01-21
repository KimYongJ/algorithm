import java.util.ArrayList;
class Solution {
    public ArrayList<Integer> solution(int[] arr, boolean[] flag) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<flag.length; i++){
            if(flag[i]){
                for(int j=0; j<arr[i]*2; j++)
                    list.add(arr[i]);
            }else{
                for(int j=0; j<arr[i]; j++)
                    list.remove(list.size()-1);   
            }
        }
        return list;
    }
}