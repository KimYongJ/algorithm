class Solution {
    static int idx;
    public String[] solution(String[] picture, int k) {
        
        String[] result = new String[picture.length * k];
        
        for(String str : picture){
            
            StringBuilder sb = new StringBuilder();
            
            for(char c : str.toCharArray())
                for(int i=0; i<k; i++) 
                    sb.append(c);
            
            for(int i=0; i<k; i++) 
                result[idx++] = sb.toString();
        }
        return result;
    }
}