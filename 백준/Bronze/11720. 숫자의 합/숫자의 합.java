import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int r = 0;
        String str = br.readLine();
        for(char c : str.toCharArray()){
            r += c-'0';
        }
        System.out.println(r);
    }
}