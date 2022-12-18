import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toLowerCase();
        int[] cl = new int[26];
        int max=0, cnt=0;
        char result =' ';
        
        for(char c : str.toCharArray()){
            if(max<++cl[c-'a']){
                max = cl[c-'a'];
                result = c;
                cnt=32;
            }else if(max == cl[c-'a'])
                cnt ++;
        }
        if(cnt==32)
            System.out.println((char)(result-cnt));
        else
            System.out.println("?");
        
        
    }
}