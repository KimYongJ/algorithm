import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] str = br.readLine().split("");
        for(int i=0; i<str.length-1; i++){
            for(int j=0; j<str.length-1-i; j++){
                if(str[j].charAt(0)<str[j+1].charAt(0)){
                    String d = str[j];
                    str[j] = str[j+1];
                    str[j+1] = d;
                }
            }
        }
        for(int x=0; x<str.length; x++)
            sb.append(str[x]);
        System.out.println(sb);
    }
}