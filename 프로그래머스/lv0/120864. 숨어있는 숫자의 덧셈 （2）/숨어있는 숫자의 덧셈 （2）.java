class Solution {
    public int solution(String s) {
        int answer = 0;
        String str ="";
        for(int i=0;i<s.length();i++){
            if('0'<=s.charAt(i)&&s.charAt(i)<='9'){
                str+=s.charAt(i)+"";
            }else{
                if(str.length()!=0){
                    answer += Integer.parseInt(str);
                    str = "";
                }        
            }
            if(i==s.length()-1){
                if(str.length()!=0)
                    answer += Integer.parseInt(str);     
            }
        }
        
        return answer;
    }
}