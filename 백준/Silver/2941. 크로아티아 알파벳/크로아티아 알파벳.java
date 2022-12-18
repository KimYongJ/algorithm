import java.io.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        str = str.replaceAll("dz="," ").replaceAll("d-"," ").replaceAll("lj"," ")
        		.replaceAll("c-"," ").replaceAll("c="," ")
        		.replaceAll("s="," ").replaceAll("z="," ")
        		.replaceAll("nj"," ");
        		
        System.out.println(str.length());
        
    }
}