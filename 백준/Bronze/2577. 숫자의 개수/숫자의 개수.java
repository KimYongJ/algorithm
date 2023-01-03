import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int r = Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine()) * Integer.parseInt(br.readLine());
        int[] result = new int[10];
        
        for(char s : String.valueOf(r).toCharArray())
            result[s-'0']++;
        
        for(int i=0;i<10;i++)
            sb.append(result[i]).append("\n");
        
        System.out.println(sb);
    }
}