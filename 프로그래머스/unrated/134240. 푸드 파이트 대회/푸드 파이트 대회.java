class Solution {
    public String solution(int[] food) {
        String str = "0";
        for(int i=food.length-1; i>0; i--)
            for(int j=0;j<food[i]/2; j++)
                str = i + str + i;

        return str;
    }
}