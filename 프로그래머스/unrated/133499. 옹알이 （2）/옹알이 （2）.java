class Solution {
    public int solution(String[] b) {
        int cnt = 0;
        String[] com = {"aya","ye","woo","ma"};
        for(String str : b){
            if(str.contains("ayaaya") || str.contains("yeye") || str.contains("woowoo") || str.contains("mama") )
                continue;
            
            for(String x: com)
                str = str.replaceAll(x," ");
            
            if(str.replaceAll(" ","").length() == 0) 
                cnt++;
        }
        return cnt;
    }
}
