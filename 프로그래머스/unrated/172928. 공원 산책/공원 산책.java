//https://github.com/KimYongJ
class Solution {
    static int x,y,mX,mY;
    public int[] solution(String[] park, String[] routes) {
        mX=park.length;
        mY=park[0].length();
        settingXY(park);
        for(String str : routes)
            change(str,park);
        return new int[]{x,y};
    }
    public void settingXY(String[] park){
        for(int i=0; i<mX; i++)
            for(int j=0; j<mY; j++)
                if(park[i].charAt(j)=='S'){
                    x=i;
                    y=j;
                    return;
                }
    }
    public void change(String str,String[] park){
        int len = str.charAt(2)-'0';
        char c = str.charAt(0);
        int xx = x;
        int yy = y;
        while(len-->0){
            int plus = 0;
            if(c=='N'|| c=='S'){
                plus = xx + (c=='N' ? -1 : 1);
                if(plus<0 || plus>=mX || park[plus].charAt(y)=='X'){
                    return;
                }
                xx = plus;
            }else{
                plus = yy + (c=='W' ? -1 : 1);
                if(plus<0 || plus>=mY || park[x].charAt(plus)=='X'){
                    return;
                } 
                yy = plus;
            }
        }
        x = xx;
        y = yy;
    }
}