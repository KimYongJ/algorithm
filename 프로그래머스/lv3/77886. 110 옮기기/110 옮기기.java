//https://github.com/KimYongJ
class Solution {
    public String[] solution(String[] s) {
        
        String[] result = new String[s.length];  
        
        for(int i=0; i<s.length; i++){
            int len = 0;
            StringBuilder sb        = new StringBuilder();
            StringBuilder str110    = new StringBuilder();
            
            for(int j=0; j<s[i].length(); j++){
                char a = s[i].charAt(j);
                sb.append(a);
                len = sb.length();
                // StrinBuilder에 값을 저장하고 저장된 값중에서 뒤에서 3번째까지 글자가 110인지 찾는다.
                if(len>=3 && sb.charAt(len-3)=='1' && sb.charAt(len-2)=='1' && sb.charAt(len-1)=='0'){
                    str110.append("110");
                    sb.delete(len-3,len);
                }
            }
            
            int idx = sb.lastIndexOf("0")+1; // 0의 마지막 위치를 구한다.
            sb.insert(idx,str110); // 0의 마지막위치가 없으면 자동적으로 맨앞에 110이 추가된다.
            result[i] = sb.toString();
        }
        return result;
    }
}