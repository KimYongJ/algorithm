import java.util.Arrays;
class Solution {
    public String solution(String my_string, int[] indices) {
        StringBuilder sb = new StringBuilder();
        Arrays.sort(indices);
        for(int i=0,j=0; i<my_string.length(); i++)
            if(j!=indices.length && i==indices[j]){
                j++;
            }else sb.append(my_string.charAt(i));
        
        return sb.toString();
    }
}