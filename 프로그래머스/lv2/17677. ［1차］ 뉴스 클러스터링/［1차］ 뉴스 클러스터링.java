import java.util.List;
import java.util.ArrayList;
class Solution {
    public int solution(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        List<String> union = new ArrayList<>();
        List<String> inter = new ArrayList<>();
        List<String> l1 = makeJK(str1);
        List<String> l2 = makeJK(str2);
        
        for(String str : l1){
            if(l2.remove(str)) inter.add(str);
            union.add(str);
        }
        for(String str : l2)
            union.add(str);
        
        double n = inter.size();
        double u = union.size();

        return u == 0 ? 65536 : (int)(n/u*65536);
    }
    public List<String> makeJK(String str){ // 다중집합 원소 만들기
        List<String> list = new ArrayList<>();
        for(int i=0; i<str.length()-1; i++){
            char c1 = str.charAt(i);
            char c2 = str.charAt(i+1);
            if(c1>='a' && c1<='z' && c2>='a' && c2<='z')  list.add(c1+""+c2);
        }
        return list;
    }
}