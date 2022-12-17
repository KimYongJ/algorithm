import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        
        for(int x ='a';x<='z';x++){
            System.out.print(str.indexOf(x)+" ");
        }
        
    }
}
