class Solution {
    public int solution(String binomial) {
        String[] token = binomial.split(" ");
        int a = Integer.parseInt(token[0])
          , b = Integer.parseInt(token[2]);

        if("+".equals(token[1]))
            return a + b;
        else if("-".equals(token[1]))
            return a - b;
        else
            return a * b;

    }
}