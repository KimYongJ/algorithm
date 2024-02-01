import java.util.Arrays;


class Solution {
    public String[] solution(String[] list) {
        for (int i = 0; i < list.length; i++)
            if (list[i].equals("l")) {
                return Arrays.copyOf(list, i);
            } else if (list[i].equals("r")) {
                if(i+1 != list.length)
                return Arrays.copyOfRange(list, i+1, list.length);
            }
        
        return new String[0];
    }
}