import java.io.*;

class Main{
    public static void main(String... args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        x = x < y ? x : y;
        y = Integer.parseInt(br.readLine());
        x = x < y ? x : y;
        y = Integer.parseInt(br.readLine());
        int z = Integer.parseInt(br.readLine());
        System.out.println(x-50+ (y<z?y:z));
    }
}