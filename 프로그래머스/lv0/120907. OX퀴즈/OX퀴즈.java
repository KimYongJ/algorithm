class Solution {
    public String[] solution(String[] quiz) {
        String[] result = new String[quiz.length];
        int cnt = 0;
        for(String str : quiz){
            String[] s = str.split(" = ");
            int cal = Integer.parseInt(s[1]);
            
            if(s[0].contains("+")){
                String[] n = s[0].split(" \\+ ");
                result [cnt++] = Integer.parseInt(n[0])+Integer.parseInt(n[1])== cal ?
                                            "O" : "X";
            }else{
                String[] n = s[0].split(" \\- ");
                result [cnt++] = Integer.parseInt(n[0])-Integer.parseInt(n[1])== cal ?
                                            "O" : "X";
            }
            
        }
        return result;        
    }
}