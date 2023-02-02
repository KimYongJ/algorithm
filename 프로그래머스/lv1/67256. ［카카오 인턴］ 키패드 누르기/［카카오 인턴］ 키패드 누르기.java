class Solution {
    public String solution(int[] n, String h) {
        StringBuilder sb = new StringBuilder();
        int left = 12,right = 12;
        for(int x : n){
            if(x==1 || x==4 || x==7){
                left = x+2;
                sb.append("L");
            }else if(x==3 || x==6 || x==9){
                right = x;
                sb.append("R");
            }else{
                if(x==0) x=11;
                int l_distance = check(Math.abs(left-x));
                int r_distance = check(Math.abs(right-x));
                
                if(l_distance<r_distance){
                    sb.append("L");
                    left = x;
                }else if(l_distance>r_distance){
                    sb.append("R");
                    right = x;
                }else if(l_distance==r_distance){
                    if(h.equals("left")){
                        sb.append("L");
                        left = x;
                    }else{
                        sb.append("R");
                        right = x;
                    }
                }
            }
        }
        return sb.toString();
    }
    public int check(int x){
        if(x<3)
            return x;
        else if(x%3==0)
            return x/3;
        else if(x==4)
            return 2;
        else if(x==5 || x== 7)
            return 3;
        else 
            return 4;
    }
}