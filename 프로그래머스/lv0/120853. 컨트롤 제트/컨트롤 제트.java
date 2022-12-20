class Solution {
    public int solution(String s) {
        String[] str = s.split(" ");
        int sum = 0;
        for(int i=0; i<str.length; i++)
            sum += str[i].equals("Z") ? 
                Integer.valueOf(str[i-1]).intValue()*-1 : 
                Integer.valueOf(str[i]).intValue();
        return sum;
    }
}