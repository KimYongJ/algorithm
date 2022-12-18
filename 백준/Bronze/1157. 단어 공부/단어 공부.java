import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toLowerCase();
        int[] cl = new int[26];
        int max = 0 ;
        String result = "";
        for(int i=0;i<str.length();i++){
            char c = str.charAt(i);
            cl[c-'a']++;
            if(max<cl[c-'a']){
                max = cl[c-'a'];
                result = c+"";
            }
        }
        int cnt = 0;
        for(int i =0;i<26;i++){
            if(max==cl[i]) cnt++;
            if (cnt==1){
                result = result.toUpperCase()+"";
            }else if(cnt>1){
                result = "?"; break;
            }
        }
        System.out.println(result);
        
    }
}