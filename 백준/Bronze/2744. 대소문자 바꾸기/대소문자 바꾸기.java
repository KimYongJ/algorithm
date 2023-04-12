import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        
        for(char c : str.toCharArray()){
            c += c<97 ? 32 : -32;
            sb.append(c);
        }
        
        System.out.println(sb.toString());
    }
}