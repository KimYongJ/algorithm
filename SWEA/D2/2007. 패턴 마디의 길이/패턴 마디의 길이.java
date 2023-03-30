import java.io.*;

class Solution{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int l = Integer.parseInt(br.readLine());
        
        for(int r = 1; r<=l; r++){
            String str = br.readLine();
            for(int i=1; i<str.length(); i++){
                String a = str.substring(0,i);
                String b = str.substring(i,i+i);
                if(a.equals(b)){
                    bw.write("#"+r+" "+a.length()+"\n");
                    break;
                }
            }
        }
        bw.flush();
    }
}