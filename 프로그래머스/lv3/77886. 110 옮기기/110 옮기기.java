//https://github.com/KimYongJ
// 전체 설명 : 문자열을 앞에서부터 훑으면서 110을 계속 뽑아낸다. 결과적으로 110만있는 문자열과 110이
// 없는 문자열을 만든다, 그 후 110이 완전히 제거된 문자열의 마지막 0을 찾아낸 후 그 뒤에 110을 붙여
// 넣는다. 이 때 마지막 0이 없다면 맨앞에 110을 붙여 넣는다. 
class Solution {
    public String[] solution(String[] s) {
        
        String[] result = new String[s.length];  // 결과 배열 선언
        
        for(int i=0; i<s.length; i++){
            int len = 0; 
            StringBuilder sb        = new StringBuilder();// 110을 제외하고 문자열을 담을 sb선언
            StringBuilder str110    = new StringBuilder();// 110을 담을 sb선언
            
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
