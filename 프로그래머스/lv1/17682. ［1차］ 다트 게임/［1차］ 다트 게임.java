import java.util.*;
class Solution {
    public int solution(String dart) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dart = dart.replaceAll("10",":")+' ';
        for(int i=0; i<dart.length()-1; i++){
            sb.append(dart.charAt(i));
            char c = dart.charAt(i+1);
            if(47<=c && c<=58 || c==' '){
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        
        int sum = 0;
        for(int i=list.size()-1; i>=0; i--)
            sum += get(list.get(i));
        
        return sum;
    }
    
    static boolean double_yn = false;
    public int get(String str){
        boolean check = false;
        int num = str.charAt(0)-48;
        for(char c : str.substring(1).toCharArray()){
            if(c=='D')
                num *= num;
            else if(c=='T')
                num *= num*num;
            else if(c=='#')
                num = -num;
            else if(c=='*'){
                num *= 2;
                check = true;
            }
        }
        if(double_yn)
            num *= 2;
        double_yn = check ? true : false;
        
        return num;
    }
}