import java.io.*;
class Solution{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] arr = new int[9][9];
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=l; i++){
            for(int a=0;a<9; a++)
                for(int b=0; b<9; b++)
                    arr[a][b] = readInt();
            sb.append("#").append(i).append(" ").append(check()).append("\n");
        }
        System.out.println(sb);
    }
    public static int check(){
        for(int i=0; i<9; i++){
            int n=0;
            for(int j=0; j<9; j++){
                n += arr[i][j];
                n -= arr[j][i];
                if(i%3==0 && j%3==0)
                    if(check(i,j)) return 0;
            }
            if(n!=0) return 0;
        }        
        return 1;
    }
    public static boolean check(int a,int b){
        boolean[] bool = new boolean[10];
        for(int i=a; i<a+3; i++){
            for(int j=b; j<b+3; j++){
                if(bool[arr[i][j]]) return true;
                bool[arr[i][j]] = true;
            }
        }
        return false;
    }
    
    public static int readInt()throws Exception{
        while(true){
            int num = br.read();
            if('0'<num && num<='9') return num-48;
        }
    }
}