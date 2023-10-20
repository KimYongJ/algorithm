// https://github.com/KimYongJ/algorithm
class Validation{
    boolean ispossible = true;
    int o=0,x=0;
    char[][] arr;
    public Validation(String[] board){
        
        this.arr = new char[3][3];
        
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++){
                arr[i][j] = board[i].charAt(j);
                if(arr[i][j]=='O') o++;
                else if(arr[i][j]=='X') x++;
            }
    }
    public Validation ox_count(){ // x가 0보다 많거나, o가 x보다 2개더 많으면 오류
        if(x>o || o>=x+2) ispossible = false;
        return this;
    }
    public Validation winCheck(){
        int owin = 0;
        int xwin = 0;
        for(int i=0; i<3; i++){// 가로 세로 승리확인
            if(arr[i][0]+arr[i][1]+arr[i][2] == 237) owin++;
            if(arr[i][0]+arr[i][1]+arr[i][2] == 264) xwin++;
            if(arr[0][i]+arr[1][i]+arr[2][i] == 237) owin++;
            if(arr[0][i]+arr[1][i]+arr[2][i] == 264) xwin++;
        }
        // 대각선 확인 
        if(arr[0][0]+arr[1][1]+arr[2][2]==237) owin++;
        else if(arr[0][0]+arr[1][1]+arr[2][2]==264) xwin++;
        if(arr[0][2]+arr[1][1]+arr[2][0]==237) owin++;
        else if(arr[0][2]+arr[1][1]+arr[2][0]==264) xwin++;
        /*
        * 불가한 경우의 수
        * 1. o와 x가 둘다 이길 때
        * 2. x가 이겼으나 x와 o의 갯수가 같을 때
        * 3. o가 이겼으나 x가o보다 같거나 클 때
        * 주의 : o가 2줄이되면서 이기는 경우의 수가 발생할 수 있습니다.
        */
        if((owin>0 && xwin>0) || 
           (xwin>0 && x!=o)   || 
           (owin>0 && o!=x+1) )
        {
            ispossible = false;
        }
        
        return this;
    }
}
class Solution {
    
    public int solution(String[] board) {
        
        Validation val = new Validation(board);
        
        val.ox_count().winCheck();
        
        return val.ispossible ? 1 : 0;
    }

}