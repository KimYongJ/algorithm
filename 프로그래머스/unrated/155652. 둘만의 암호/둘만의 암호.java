class Solution {
    public String solution(String s, String skip, int idx) {
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray()){
            for(int i=0; i<idx; i++){
                if(++c>122) c = 'a';
                if(skip.contains(c+"")) --i;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
