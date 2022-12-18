import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int len = 0;
        for(String s : str){
            if(s.equals(""))
                continue;
            len++;
        }
        System.out.println(len);
        
    }
}