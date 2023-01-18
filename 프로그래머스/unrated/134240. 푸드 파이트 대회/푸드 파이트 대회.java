class Solution {
    public String solution(int[] food) {
        String str1 = "";
        for(int i=1; i<food.length; i++)
            for(int j=0;j<food[i]/2; j++)
                str1 += String.valueOf(i);

        return str1+"0"+new StringBuffer(str1).reverse().toString();
    }
}