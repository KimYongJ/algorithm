import java.io.*;

class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        boolean[] b = new boolean[10000001];
        int[] cnt = new int[10000001];
        int l = Integer.parseInt(br.readLine());
        
        for(int i=0; i<l; i++){
            short num = Short.parseShort(br.readLine());
            b[num] = true;
            cnt[num]++;
        }
        for(int j=0; j<10000001; j++)
            if(b[j])
                for(int x=0; x<cnt[j]; x++)
                    sb.append(j).append("\n");
        
        System.out.println(sb);
    }
}