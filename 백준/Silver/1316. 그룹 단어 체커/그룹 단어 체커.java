import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0, len = Integer.parseInt(br.readLine());
        for(int i=0; i<len; i++)
            result += comp(br.readLine());
        System.out.println(result);
    }
    public static int comp(String str){
        boolean[] arr = new boolean[26];
        int len = str.length();      
        for(int i=0; i<len; i++){
            if(len !=  i+1 && str.charAt(i)==str.charAt(i+1))
                     continue;
            if(arr[str.charAt(i)-'a'])
                 return 0;
            arr[str.charAt(i)-'a'] = true;
         }   
         return 1;
    }
}
