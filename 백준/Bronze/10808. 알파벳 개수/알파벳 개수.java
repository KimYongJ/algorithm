import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        int[] arr = new int [26];
        

        for(char c : str.toCharArray())
            arr[c-97]++;
        
        for(int x : arr)
            sb.append(x).append(" ");
        System.out.println(sb);
    }
}