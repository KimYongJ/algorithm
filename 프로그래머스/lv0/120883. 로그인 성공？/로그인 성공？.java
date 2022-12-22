import java.util.*;

class Solution {
   public String solution(String[] id, String[][] db) {
       Map<String,String> map = new HashMap<String,String>();
       
       for(String[] str : db)
           map.put(str[0],str[1]);
       
       if(map.containsKey(id[0])){
           if(map.get(id[0]).equals(id[1]))
               return "login";
           return "wrong pw";
       }else{
           return "fail";
       }
   }
}