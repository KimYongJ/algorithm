class Solution {
    public int solution(String s) {
        String [] xl = s.replaceAll("[a-zA-Z]"," ").split(" ");
        int result =0;
        for(String x : xl){
            if(!x.equals(""))
            result+=Integer.parseInt(x);
        }
        return result;
    }
}
