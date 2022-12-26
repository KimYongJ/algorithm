import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out),32768);
        int[] arr = new int[10001];
        int l = Integer.parseInt(br.readLine());
        
        for(int i=0; i<l; i++)
            arr[Integer.parseInt(br.readLine())]++;
        
        br.close();
        
        for(int j=0; j<10001; j++)
            if(arr[j]!=0)
                bw.write((j+"\n").repeat(arr[j]));
        bw.flush();
        bw.close();
    }
}