import java.io.*;
import java.util.*;
class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int l = Integer.parseInt(br.readLine());
        String[] str = new String[l];
        for(int q=0; q<l; q++)
            str[q] = br.readLine();        
        
        Arrays.sort(str, new Comparator<String>(){
            public int compare(String a, String b){
                if(a.length()==b.length()){
                    return a.compareTo(b);
                }else{
                    return a.length()-b.length();
                }
            }
        });
        sb.append(str[0]).append("\n");
        for(int i=1;i<l;i++)
            if(!str[i].equals(str[i-1]))
                sb.append(str[i]).append("\n");
        
        System.out.println(sb);
    }
}