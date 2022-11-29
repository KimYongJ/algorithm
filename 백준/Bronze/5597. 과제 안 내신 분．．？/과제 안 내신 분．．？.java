import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int[] arr= new int[31];
        for(int i=0;i<28;i++){
            int data = Integer.parseInt(in.readLine());
            arr[data]=1;
        }
        for(int i=1;i<31;i++){
            if(arr[i]!=1) System.out.println(i);
        }
        
    }
}