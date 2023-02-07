class Solution {
  public int solution(String dartResult) {
      String numTemp="";
        int Answer=0;

        int lastScore=0;
        int num=0;

        char next=' ';

        for(int i=0;i<dartResult.length();i++){
            next=dartResult.charAt(i);
            if(next=='S'||next=='D'||next=='T'){
                lastScore=num;
                num=Integer.parseInt(numTemp);
                if(next=='D'){
                    num*=num;
                }else if(next=='T'){
                    num=num*num*num;
                }
                Answer+=num;
                numTemp="";
            }else if(next=='#'){
                num=-num;
                Answer+=2*num;
            }else if(next=='*'){
                Answer+=num+lastScore;
                num=2*(num);
            }else{
                numTemp=numTemp+next;
            }
        }

      return Answer;
  }
}