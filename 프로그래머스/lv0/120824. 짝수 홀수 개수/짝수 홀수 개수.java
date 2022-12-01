import java.io.*;
import java.util.*;
class Solution {
    public int[] solution(int[] num_list)throws Exception {
        int[] result = new int[2];
        for(int i=0;i<num_list.length;i++){
            if(num_list[i]%2==0){
                result[0]+=1;
            }else{
                result[1]+=1;
            }
        }
        return result;
        
    }
}