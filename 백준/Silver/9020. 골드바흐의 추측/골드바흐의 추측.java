import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        boolean[] check = new boolean[10001];
        check[0] = check[1] = true;
        for(int x=2; x<10001; x++)
            for(int i=2; i*i<=x; i++)
                if(x%i==0){ 
                    check[x] = true;
                    break;
                }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int len = Integer.parseInt(br.readLine());
        for(int i=0; i<len; i++){
            int n = Integer.parseInt(br.readLine());
            for(int j=n/2 ;j>=2;j--)
                if(!check[j] && !check[n-j]){
                    sb.append(j).append(" ").append(n-j).append("\n");
                    break;
                }      
        }
        System.out.println(sb);
    }
}