import java.io.*;

class Main{
    public static boolean[] check = new boolean[10001];
    public static void main(String[] args)throws Exception{
        checked();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        for(int i=0; i<len; i++){
            int n = Integer.parseInt(br.readLine());
            int a=0,b=0;
            for(int j=n/2 ;j>=2;j--){
                if(check[j] && check[n-j]){
                    a = j;
                    b = n-j;
                    break;
                }      
            }
            sb.append(a).append(" ").append(b).append("\n");
        }
        System.out.println(sb);
    }
    public static void checked(){
        for(int x=2; x<10001; x++){
            boolean c = true;
            for(int i=2; i*i<=x; i++){
                if(x%i==0){ 
                    c = false;
                    break;
                }
            }
            check[x] = c;
        }
    }
}