// https://github.com/KimYongJ/algorithm
class Solution {
    
    private String result="(None)";
    
    public String solution(String m, String[] mu) {
        
        int maxPlayTime = 0;
        
        m = replaceAll(m);// 샵(#)이 들어간 문자열을 치환한다.

        for(String part : mu){
            String[] arr = part.split(",");
            
            int playTime = getPlayTime(arr[0],arr[1]); // 플레이 시간을 분으로 치환
            
            String scale = replaceAll(arr[3]);
            
            int len = scale.length();
            
            if(len<playTime){ // 주어진 음계보다 음악 길이가 더 길 때 
                StringBuilder sb = new StringBuilder();
                for(int i=0; i<playTime; i++){
                    sb.append(scale.charAt(i%len));
                }
                scale = sb.toString();
            }else{
                scale = scale.substring(0,playTime);
            }
            if(scale.contains(m) && playTime>maxPlayTime){
                result = arr[2];
                maxPlayTime = playTime;
            }
        }
        return result;
    }
    // 문자열 치환 규칙 : C# = H , D# = I , F# = J , G# = K , A# = L
    public String replaceAll(String str){
        return str.replaceAll("C#","H").replaceAll("D#","I").replaceAll("F#","J")
                    .replaceAll("G#","K").replaceAll("A#","L");
    }
    // 플레이시간 구하는 함수
    public int getPlayTime(String str1,String str2){ 
        int start = Integer.parseInt(str1.substring(0,2))*60 + 
                    Integer.parseInt(str1.substring(3));
        int end   = Integer.parseInt(str2.substring(0,2))*60 + 
                    Integer.parseInt(str2.substring(3));
        return end-start;
    }
}