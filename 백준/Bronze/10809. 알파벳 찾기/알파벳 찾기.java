import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        char[] cl = br.readLine().toCharArray();
        int[] arr = new int[26];
        
        for(int i=0;i<cl.length;i++){
            if(arr[cl[i]-'a']==0){
                arr[cl[i]-'a'] = i+1;
                continue;
            }
        }
        for(int j=0;j<26;j++){
            sb.append(--arr[j]).append(" ");
        }
        System.out.println(sb);
        
    }
}