class Solution {
    public String[] solution(String[] quiz) {
        for(int i=0; i<quiz.length; i++){
            String[] str = quiz[i].split(" ");
            int x = Integer.parseInt(str[0]);
            int y = Integer.parseInt(str[2]);
            int result = Integer.parseInt(str[4]);
            quiz[i] = str[1].equals("+") ? 
                            (x+y == result ? "O" : "X") :
                            (x-y == result ? "O" : "X");
        }
        return quiz;
    }
}
