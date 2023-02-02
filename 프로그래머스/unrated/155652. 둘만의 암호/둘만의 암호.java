class Solution {
    public String solution(String s, String skip, int idx) {
        StringBuilder sb = new StringBuilder();
        String str = "abcdefghijklmnopqrstuvwxyz";
        for(char c : skip.toCharArray())
            str = str.replace(c+"","");
        int arrlen = str.length();
        char[] arr = new char[arrlen];
        for(int i=0; i<arrlen; i++)
            arr[i] = str.charAt(i);
        
        for(char c : s.toCharArray()){
            int p = 0;
            
            for(int i=0; i<arrlen; i++)
                if(arr[i]==c){
                    p=i+(idx%arrlen);
                    break;
                }                
            if(arrlen<=p){
                p -= arrlen;
            }
            sb.append(arr[p]);
        }
        
        return sb.toString();
    }
}