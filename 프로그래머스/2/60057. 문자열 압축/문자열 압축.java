// https://github.com/KimYongJ/algorithm
class Solution {
    
    int result = Integer.MAX_VALUE; // 결과를 반환할 값
    
    public int solution(String s) {
        if(s.length()<3)  // 길이가 2이하일 경우 줄여도 길이가 같기 때문에 예외처리 해줌
            return s.length();
        
        for(int i=1; i<s.length()/2+1; i++){ // 문자열의 절반 이상으로 압축은 불가함
            compression(s,i);
        }
        
        return result;
    }
    public void compression(String s, int i){
        
        int len = s.length(); // 압축된 문자열 길이
        
        String base = s.substring(0,i); // 압축할 문자
        
        int cnt = 1; // 압축문자 갯수
        
        for(int x=i; x<s.length(); x+=i){
            
            if(x+i>s.length()) break; // 문자열 범위초과 예외 처리
            
            String next = s.substring(x,x+i); // 비교 문자열 추출
            
            if(base.equals(next)){ // 압축 가능여부 파악
                len-=i; // 총 길이에서 압축문자 길이를 뺀다.
                cnt++; // 압축 했으면 +1
            }else{
                base = next; // 압축이 불가할 경우 현재 문자열은 다음 압축할 문자로 바꿈
                if(cnt>1){// 압축이 불가한경우 이전까지 압축한내용이 있다면 이하 코드 실행
                    len += (cnt+"").length();
                    cnt = 1;
                }
            }
        }
        if(cnt>1){ // 위의 for문이 끝나고 나머지 추가 압축된거 계산해줌
            len += (cnt+"").length();
        }
        
        result = Math.min(result,len); // 가장 짧은 경우 result 값 갱신
    }
}