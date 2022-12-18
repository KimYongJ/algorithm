import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toLowerCase();
        int[] cl = new int[26];
        int max = 0;
        int result =' ';
        
        for(char c : str.toCharArray()){
            if(max<++cl[c-'a']){
                max = cl[c-'a'];
                result = c-32;
            }else if(max == cl[c-'a'])
                result = 63;
        }
            System.out.println((char)result);
    }
}
