import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine().toLowerCase();
        int[] cl = new int[26];
        for(char c : str.toCharArray()){
            cl[c-'a']++;
        }
        int max = 0;
        int idx = 0;
        for(int i=0;i<26;i++){
            if(max<cl[i]){
                max = cl[i];
                idx = i;
            }
        }
        int cnt=0;
        for(int j=0;j<26;j++){
            if(max==cl[j]) cnt++;
        }
        if(cnt==1)
            System.out.println((char)(idx+'A'));
        else
            System.out.println("?");
        
    }
}