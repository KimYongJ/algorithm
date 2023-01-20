class Solution {
    public int solution(String[] b) {
        int cnt = 0;
        String[] except = {"ayaaya","yeye","woowoo","mama"};
        String[] com = {"aya","ye","woo","ma"};
        for(String str : b){
            for(String x: except)
                str = str.replaceAll(x,"1");
            
            for(String x: com)
                str = str.replaceAll(x,"2");
            
            boolean check = true;
            for(char c: str.toCharArray())
                if('2' != c) check = false;
            if(check) cnt++;
        }
        return cnt;
    }
}