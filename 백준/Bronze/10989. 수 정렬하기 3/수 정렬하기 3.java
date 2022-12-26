import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[10001];
        int l = Integer.parseInt(br.readLine());
        
        for(int i=0; i<l; i++)
            arr[Integer.parseInt(br.readLine())]++;

        for(int j=0; j<10001; j++)
            for(int x=0; x<arr[j]; x++)
                sb.append(j).append("\n");

        System.out.println(sb);
    }
}