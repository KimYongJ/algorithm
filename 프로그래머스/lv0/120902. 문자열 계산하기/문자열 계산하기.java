class Solution {
    public int solution(String s) {
        String[] sl = s.split(" ");
        int result =Integer.parseInt(sl[0]) ;
        for(int i=1;i<sl.length;i++){
            if(sl[i].equals("+"))
                result += Integer.parseInt(sl[i+1]);
            else if(sl[i].equals("-"))
                result -= Integer.parseInt(sl[i+1]);
        }
        return result;
    }
}