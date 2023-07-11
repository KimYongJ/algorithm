import java.util.LinkedList;
class Solution {
    public int solution(int size, String[] city) {
        if(size==0) return city.length*5;
        int result = 0;
        LinkedList<String> link = new LinkedList();
        for(String str : city){
            str = str.toLowerCase();
           if(link.contains(str)){
               result += 1;
               link.remove(link.indexOf(str));
           }else{
               result += 5;
               if(link.size()==size){
                   link.removeLast();
               }
           }
            link.add(0,str);
        }
        return result;
    }
}