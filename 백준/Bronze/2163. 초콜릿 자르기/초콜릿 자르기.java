import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        System.out.println(Integer.parseInt(str[0])*
                           Integer.parseInt(str[1])-1);
    }
}