import java.io.*;

class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] r = new boolean[2001];
        int len = Integer.parseInt(br.readLine());
        for(int i=0;i<len;i++)
            r[Integer.parseInt(br.readLine())+1000] = true;
        for(int x=0;x<2001;x++)
            if(r[x]) sb.append(x-1000).append("\n");
        System.out.println(sb);
    }
}