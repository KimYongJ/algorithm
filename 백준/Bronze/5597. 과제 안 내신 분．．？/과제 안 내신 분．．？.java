import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean[] arr= new boolean[31];
        for(int i=0;i<28;i++) 
            arr[Integer.parseInt(in.readLine())]=true;
        for(int i=1;i<31;i++)
            if(!arr[i]) System.out.println(i);

        
    }
}