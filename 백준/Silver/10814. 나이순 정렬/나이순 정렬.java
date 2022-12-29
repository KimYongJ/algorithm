import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int l = Integer.parseInt(br.readLine());
        String[][] str = new String[l][2];
        for(int q=0; q<l; q++){
            String[] s = br.readLine().split(" ");
            str[q][0]=s[0];
            str[q][1]=s[1];
        }
        
        Arrays.sort(str,new Comparator<String[]>(){
            public int compare(String[] a,String[] b){
					return Integer.parseInt(a[0]) - Integer.parseInt(b[0]);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<l; i++)
            sb.append(str[i][0]+" "+str[i][1]+"\n");
        System.out.println(sb);          
    }
}